package com.chatterly.automation_service.entity;

import java.io.Serializable;

import com.chatterly.automation_service.enums.MediaType;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String postid;

    @Column
    private String caption;

    @Column(nullable = false)
    private String media;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MediaType mediaType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "automation_id", nullable = false)
    @JsonBackReference
    private Automation automation;

    public Post(String postid, String caption, String media, MediaType mediaType, Automation automation) {
        this.postid = postid;
        this.caption = caption;
        this.media = media;
        this.mediaType = mediaType;
        this.automation = automation;
    }
}
