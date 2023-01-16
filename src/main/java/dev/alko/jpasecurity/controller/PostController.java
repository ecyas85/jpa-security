package dev.alko.jpasecurity.controller;

import dev.alko.jpasecurity.model.Post;
import dev.alko.jpasecurity.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostRepository postRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("{id}")
    public Post getPostById(@PathVariable("id") Post post) {
        return post;
    }
}
