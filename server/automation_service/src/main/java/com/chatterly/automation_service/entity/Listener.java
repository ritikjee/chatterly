package com.chatterly.automation_service.entity;

import java.io.Serializable;

import com.chatterly.automation_service.enums.Listeners;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "listener")
public class Listener implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "automation_id", nullable = false, unique = true)
    private Automation automation;

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
