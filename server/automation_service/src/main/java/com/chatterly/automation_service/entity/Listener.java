package com.chatterly.automation_service.entity;

import com.chatterly.automation_service.enums.Listeners;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "listener")
public class Listener {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(nullable = false, unique = true)
    private String automationId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Listeners listener;

    @Column(nullable = false)
    private String prompt;

    @Column
    private String commentReply;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int dmCount;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int commentCount;
}
