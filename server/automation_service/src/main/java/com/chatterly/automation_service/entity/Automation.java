package com.chatterly.automation_service.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "automation")
public class Automation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'Untitled'")
    private String name;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean active;

    @OneToMany(mappedBy = "automation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trigger> triggers;

    @OneToOne(mappedBy = "automation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Listener listener;

    @OneToMany(mappedBy = "automation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "automation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dms> dms;

    @Column(nullable = false)
    private String userId;

    @OneToMany(mappedBy = "automation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Keyword> keywords;
}
