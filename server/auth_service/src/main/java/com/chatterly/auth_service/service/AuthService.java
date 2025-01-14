package com.chatterly.auth_service.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatterly.auth_service.dto.MessageDTO;
import com.chatterly.auth_service.entity.User;
import com.chatterly.auth_service.repo.UserRepository;
import com.chatterly.auth_service.utils.MessageProducer;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MessageProducer messageProducer;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
            MessageProducer messageProducer) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.messageProducer = messageProducer;
    }

    @Override
    @Cacheable(value = "users-details", key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAndVerified(username, true);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(user.getPassword());

        return userBuilder.build();
    }

    @Cacheable(value = "users", key = "#email")
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User register(User user) {
        String email = user.getEmail();
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new RuntimeException("Please provide a valid email");
        }

        String password = user.getPassword();
        if (password == null || password.isEmpty() || password.length() < 8) {
            throw new RuntimeException("Please provide a valid password");
        }

        User userExists = userRepository.findByEmail(user.getEmail());
        if (userExists != null) {
            throw new RuntimeException("User with email already exists");
        }

        String verificationToken = UUID.randomUUID().toString();

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setVerificationToken(verificationToken);
        messageProducer.sendMessage(new MessageDTO(user.getEmail(), verificationToken));

        return userRepository.save(user);
    }

    @CacheEvict(value = "users", key = "#email")
    public boolean verifyUser(String email, String token) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        if (!user.getVerificationToken().equals(token)) {
            throw new RuntimeException("Invalid token");
        }

        user.setVerified(true);
        userRepository.save(user);
        return true;
    }

    @CacheEvict(value = "users", key = "#email")
    public String generateVerificationToken(String email) {
        User user = userRepository.findByEmailAndVerified(email, false);
        if (user == null) {
            throw new RuntimeException("No unverified user not found with email");
        }

        if (user.getTokenExpiry().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Token already generated");
        }

        String token = UUID.randomUUID().toString();
        userRepository.save(user);

        messageProducer.sendMessage(new MessageDTO(email, token));

        return token;
    }
}
