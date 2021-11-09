package com.devcourse.bbs.controller;

import com.devcourse.bbs.controller.bind.ApiResponse;
import com.devcourse.bbs.controller.bind.PostCreateRequest;
import com.devcourse.bbs.controller.bind.PostUpdateRequest;
import com.devcourse.bbs.domain.post.PostDTO;
import com.devcourse.bbs.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostDTO>>> getPostsByPage
            (Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(postService.findPostsByPage(pageable.getPageNumber(), pageable.getPageSize())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDTO>> getPost(@PathVariable(name = "id") long id) {
        PostDTO postDTO = postService.findPostById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Post with given id not found.");
        });
        return ResponseEntity.ok(ApiResponse.success(postDTO));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostDTO>> createPost(@Valid @RequestBody PostCreateRequest request) {
        PostDTO post = postService.createPost(request);
        URI createdPostURI = URI.create(String.format("/posts/%d", post.getId()));
        return ResponseEntity.created(createdPostURI).body(ApiResponse.success(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDTO>> updatePost(
            @PathVariable(name = "id") long id,
            @Valid @RequestBody PostUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(postService.updatePost(id, request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable(name = "id") long id) {
        postService.deletePost(id);
    }
}