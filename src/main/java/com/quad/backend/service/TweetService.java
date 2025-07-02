package com.quad.backend.service;

import com.quad.backend.dto.TweetRequest;
import com.quad.backend.model.*;
import com.quad.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Autowired
    public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public Tweet createTweet(TweetRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tweet tweet = new Tweet();
        tweet.setText(request.getText());
        tweet.setAnonymous(request.isAnonymous());
        tweet.setMediaUrls(request.getMediaUris());
        tweet.setCreatedAt(LocalDateTime.now());
        tweet.setUser(user);

        if (request.getPoll() != null) {
            Poll poll = new Poll();
            poll.setOptions(request.getPoll().getOptions());
            poll.setDurationDays(Integer.parseInt(request.getPoll().getDuration()));
            tweet.setPoll(poll);
        }

        return tweetRepository.save(tweet);
    }

    public List<Tweet> getFeed() {
        return tweetRepository.findAllByOrderByCreatedAtDesc();
    }
}