package com.chatterly.auth_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatterly.auth_service.dto.ErrorResponseDTO;
import com.chatterly.auth_service.dto.ResponseDTO;
import com.chatterly.auth_service.dto.UserInputDTO;
import com.chatterly.auth_service.entity.User;
import com.chatterly.auth_service.service.AuthService;
import com.chatterly.auth_service.utils.JwtUtils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService userService, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.authService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserInputDTO user) {

        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setImage(user.getImage());

        try {
            authService.register(newUser);
            return ResponseEntity.ok(new ResponseDTO<String>(HttpStatus.OK, "User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserInputDTO user, HttpServletResponse response,
            HttpServletRequest request) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String ipAddress = request.getHeader("X-Forwarded-For");
            if (ipAddress == null || ipAddress.isEmpty()) {
                ipAddress = request.getRemoteAddr();
            }

            String userAgent = request.getHeader("User-Agent");

            String token = jwtUtils.generateToken(userDetails, ipAddress, userAgent);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 10);
            cookie.setPath("/");
            cookie.setSecure(true);

            response.addCookie(cookie);

            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "Login successful"));
        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body(new ErrorResponseDTO(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
        }
    }

    @PostMapping("/verify-account")
    public ResponseEntity<?> getMethodName(@RequestParam String email, @RequestParam String token) {
        try {
            authService.verifyUser(email, token);
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "User verified successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @GetMapping("/generate-token")
    public ResponseEntity<?> generateNewToken(@RequestParam String email) {
        try {
            String token = authService.generateVerificationToken(email);
            return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, token));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

}