package com.quad.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String username;
    private String displayName;
    private String avatar;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int likes = 0;
    private int retweets = 0;
    private int comments = 0;

    private String emojiReaction; // e.g. 😊

    private LocalDateTime timestamp = LocalDateTime.now();

    // Getters and Setters
}