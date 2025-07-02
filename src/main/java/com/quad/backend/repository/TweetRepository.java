package com.quad.backend.repository;

import com.quad.backend.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findAllByOrderByCreatedAtDesc();
}