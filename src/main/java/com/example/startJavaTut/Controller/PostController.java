package com.example.startJavaTut.Controller;

import com.example.startJavaTut.DTO.PostDto;
import com.example.startJavaTut.Response.PostResponse;
import com.example.startJavaTut.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAll() {
        List<PostResponse> allPosts = postService.getAll();

        return ResponseEntity.ok(allPosts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PostResponse>> getById(@PathVariable Long id) {
        Optional<PostResponse> postResponse = postService.getPostById(id);
        if(postResponse.isPresent()) {
            return ResponseEntity.ok(postResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@RequestBody PostDto postDto) {
        PostResponse newPost = postService.create(postDto);
        return  new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<PostResponse>> deleteById(@PathVariable Long id) {
        postService.delete(id);

        return ResponseEntity.notFound().build();
    }
}
