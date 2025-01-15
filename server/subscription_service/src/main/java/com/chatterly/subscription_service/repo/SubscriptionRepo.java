package com.chatterly.subscription_service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatterly.subscription_service.entity.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, String> {
    Optional<Subscription> findByUserId(String userId);

}
