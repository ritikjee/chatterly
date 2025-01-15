package com.chatterly.automation_service.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "trigger")
@AllArgsConstructor
@NoArgsConstructor
public class Trigger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "automation_id", nullable = false)
    @JsonBackReference
    private Automation automation;

    public Trigger(String type, Automation automation) {
        this.type = type;
        this.automation = automation;
    }
}
