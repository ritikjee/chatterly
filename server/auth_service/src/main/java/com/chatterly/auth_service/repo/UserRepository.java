package com.chatterly.auth_service.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chatterly.auth_service.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

    @Cacheable(value = "users", key = "#email")
    User findByEmailAndVerified(String email, Boolean verified);

}
