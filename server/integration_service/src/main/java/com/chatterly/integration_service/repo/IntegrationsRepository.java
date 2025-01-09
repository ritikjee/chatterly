package com.chatterly.integration_service.repo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.chatterly.integration_service.entity.Integrations;

public interface IntegrationsRepository extends JpaRepository<Integrations, String> {
    Optional<Integrations> findByUserId(String userId);

    @Modifying
    @Transactional
    @Query("UPDATE Integrations i SET i.token = :newToken, i.expiresAt = :newExpiration WHERE i.userId = :userId")
    boolean updateTokenAndExpirationByUserId(String userId, String newToken, LocalDateTime newExpiration);

}
