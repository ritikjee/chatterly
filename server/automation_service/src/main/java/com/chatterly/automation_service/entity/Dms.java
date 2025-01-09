package com.chatterly.automation_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dms")
public class Dms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "automation_id", nullable = false)
    private Automation automation;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private String senderId;

    @Column
    private String receiver;

    @Column
    private String message;
}
