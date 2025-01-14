package com.chatterly.automation_service.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chatterly.automation_service.entity.Automation;

@Repository
public interface AutomationRepository extends JpaRepository<Automation, String> {
    // TODO : enable automation DTO
    List<Automation> findByUserId(String userId);

    Optional<Automation> findByIdAndUserId(String id, String userId);

    @Modifying
    @Transactional
    @Query("UPDATE Automation a SET a.name = :name WHERE a.id = :id AND a.userId = :userId")
    boolean updateNameById(String id, String userId, String name);

    @Modifying
    @Transactional
    @Query("UPDATE Automation a SET a.active = :active WHERE a.id = :id")
    boolean updateActiveById(String id, String userId, boolean active);

}
