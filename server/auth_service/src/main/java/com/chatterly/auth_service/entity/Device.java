package com.chatterly.auth_service.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "devices")
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "device_type", nullable = false)
    private String deviceType;

    @Column(name = "os_type", nullable = false)
    private String os;

    @Column(name = "login_time", nullable = false)
    private LocalDateTime loginTime;

    @Column(name = "device_agent")
    private String deviceAgent;

    @Column(name = "session_id", nullable = false, unique = true)
    private String sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    public void prePersist() {
        this.loginTime = LocalDateTime.now();
        this.sessionId = UUID.randomUUID().toString();
    }
}
