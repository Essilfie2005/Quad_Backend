package com.quad.backend.controller;

import com.quad.backend.model.Post;
import com.quad.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    // 1. Get all posts
    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 2. Create a new post
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    // 3. Like a post
    @PostMapping("/{id}/like")
    public Post likePost(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setLikes(post.getLikes() + 1);
        return postRepository.save(post);
    }

    // 4. Retweet a post
    @PostMapping("/{id}/retweet")
    public Post retweetPost(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setRetweets(post.getRetweets() + 1);
        return postRepository.save(post);
    }

    // 5. Add a comment (just increments count for now)
    @PostMapping("/{id}/comment")
    public Post commentPost(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setComments(post.getComments() + 1);
        return postRepository.save(post);
    }

    // 6. Add/Change Emoji Reaction
    @PostMapping("/{id}/react")
    public Post reactWithEmoji(@PathVariable Long id, @RequestBody String emoji) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setEmojiReaction(emoji);
        return postRepository.save(post);
    }
}