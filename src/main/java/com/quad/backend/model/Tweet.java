package com.quad.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private boolean anonymous;

    @ElementCollection
    private List<String> mediaUrls;

    @Embedded
    private Poll poll;

    private LocalDateTime createdAt;

    @ManyToOne
    private User user;
}