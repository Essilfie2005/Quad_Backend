package com.quad.backend.controller;

import com.quad.backend.dto.TweetRequest;
import com.quad.backend.model.Tweet;
import com.quad.backend.security.JwtUtil;
import com.quad.backend.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    private final TweetService tweetService;
    private final JwtUtil jwtUtil;

    @Autowired
    public TweetController(TweetService tweetService, JwtUtil jwtUtil) {
        this.tweetService = tweetService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> postTweet(@RequestBody TweetRequest request,
                                       @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userEmail = jwtUtil.extractUsername(token);

        Tweet tweet = tweetService.createTweet(request, userEmail);
        return ResponseEntity.ok(tweet);
    }

    @GetMapping("/feed")
    public ResponseEntity<List<Tweet>> getFeed() {
        return ResponseEntity.ok(tweetService.getFeed());
    }
}