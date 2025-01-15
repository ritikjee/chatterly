package com.chatterly.subscription_service.services;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.chatterly.subscription_service.entity.Subscription;
import com.chatterly.subscription_service.repo.SubscriptionRepo;

@Service
public class SubscriptionService {

    private final SubscriptionRepo subscriptionRepo;

    public SubscriptionService(SubscriptionRepo subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
    }

    @Cacheable(value = "subscriptions", key = "#userId")
    public Subscription getSubscriptionByUserId(String userId) {
        return subscriptionRepo.findByUserId(userId)
                .orElseGet(() -> {
                    Subscription newSubscription = new Subscription();
                    newSubscription.setUserId(userId);
                    return subscriptionRepo.save(newSubscription);
                });
    }

}
