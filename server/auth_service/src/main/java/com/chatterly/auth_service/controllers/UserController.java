package com.chatterly.auth_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatterly.auth_service.dto.ErrorResponseDTO;
import com.chatterly.auth_service.dto.ResponseDTO;
import com.chatterly.auth_service.dto.UserDTO;
import com.chatterly.auth_service.entity.User;
import com.chatterly.auth_service.service.AuthService;
import com.chatterly.auth_service.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getAuthenticatedUser() {

        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            if (userDetails == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponseDTO(HttpStatus.UNAUTHORIZED.value(), "User not found"));
            }

            System.out.println("UserDetails: " + userDetails);

            User user = authService.getUserByEmail(userDetails.getUsername());

            UserDTO userDTO = new UserDTO();

            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFirstname(user.getFirstname());
            userDTO.setLastname(user.getLastname());
            userDTO.setImage(user.getImage());

            return ResponseEntity.ok(new ResponseDTO<UserDTO>(HttpStatus.OK, userDTO));

        } catch (Exception e) {

            return ResponseEntity.badRequest()
                    .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String sessionId = request.getAttribute("sessionId").toString();

        userService.logout(userDetails.getUsername(), sessionId);

        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "Logout successful, cookie cleared"));
    }

    @PostMapping("/logout-all")
    public ResponseEntity<?> logoutAllDevices(HttpServletRequest request, HttpServletResponse response) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        userService.logoutAllDevices(userDetails.getUsername());

        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK, "Logout successful, cookie cleared"));

    }

    @GetMapping("/get-all-devices")
    public ResponseEntity<?> getAllDevices() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
            return ResponseEntity
                    .ok(new ResponseDTO<>(HttpStatus.OK, userService.getAllDevices(userDetails.getUsername())));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

}