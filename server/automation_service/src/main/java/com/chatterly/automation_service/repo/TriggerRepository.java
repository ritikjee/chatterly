package com.chatterly.automation_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatterly.automation_service.entity.Trigger;

@Repository
public interface TriggerRepository extends JpaRepository<Trigger, String> {

}
