package com.example.startJavaTut.Service;

import com.example.startJavaTut.DTO.PostDto;
import com.example.startJavaTut.Entity.Post;
import com.example.startJavaTut.Repository.PostRepository;
import com.example.startJavaTut.Response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostResponse create(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        Post newPost = postRepository.save(post);

        return this.mapToResponse(newPost);
    }

    public List<PostResponse> getAll() {
        return postRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Optional<PostResponse> getPostById(Long id) {
        return postRepository.findById(id).map(this::mapToResponse);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    private PostResponse mapToResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getDescription(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
