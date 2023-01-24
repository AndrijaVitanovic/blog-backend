package com.blog.api;

import com.blog.entity.Post;
import com.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PreAuthorize("isFullyAuthenticated()")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    @Operation(summary = "Get all posts", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Post>> getAllCategories() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets post by id", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Creates a new post", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(post));
    }

    @PutMapping
    @Operation(summary = "Updates a existing post", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.update(post));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes post by id", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
