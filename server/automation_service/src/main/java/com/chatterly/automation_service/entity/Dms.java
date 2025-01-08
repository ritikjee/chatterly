package com.chatterly.automation_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dms")
public class Dms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column
    private String automationId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private String senderId;

    @Column
    private String receiver;

    @Column
    private String message;
}
