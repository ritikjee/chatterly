package com.chatterly.auth_service.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.chatterly.auth_service.entity.Device;
import com.chatterly.auth_service.entity.User;
import com.chatterly.auth_service.repo.DeviceRepository;
import com.chatterly.auth_service.repo.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import ua_parser.Client;
import ua_parser.Parser;

@Service
public class JwtUtils {

    private String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret";

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final Parser parser;

    public JwtUtils(UserRepository userRepository, DeviceRepository deviceRepository, Parser parser) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.parser = parser;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(io.jsonwebtoken.security.Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private String getSessionId(String token) {
        return extractAllClaims(token).get("sid", String.class);
    }

    public String getUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsername(token);
        final String tokenSessionId = getSessionId(token);

        User user = userRepository.findByEmail(username);
        if (user == null) {
            return false;
        }

        boolean sessionValid = user.getDevices().stream()
                .anyMatch(device -> device.getSessionId().equals(tokenSessionId));

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token) && sessionValid);
    }

    public String generateToken(UserDetails userDetails, String ipAddress, String deviceType) {
        User user = userRepository.findByEmail(userDetails.getUsername());

        Device device = deviceRepository.findByUserAndIpAddressAndDeviceType(user, ipAddress, deviceType)
                .orElseGet(() -> {
                    Client c = parser.parse(deviceType);
                    Device newDevice = new Device();
                    newDevice.setIpAddress(ipAddress);
                    newDevice.setDeviceType(c.device.family);
                    newDevice.setOs(c.os.family);
                    newDevice.setDeviceAgent(c.userAgent.family);
                    newDevice.setUser(user);
                    return deviceRepository.save(newDevice);
                });

        Map<String, Object> claims = new HashMap<>();
        claims.put("sid", device.getSessionId());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 10))
                .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

}
