package com.blog.api;

import com.blog.entity.Post;
import com.blog.entity.Tag;
import com.blog.service.TagService;
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
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;

    @PreAuthorize("permitAll()")
    @GetMapping
    @Operation(summary = "Get all tags", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Tag>> getAllCategories() {
        return ResponseEntity.ok(tagService.findAll());
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    @Operation(summary = "Gets tag by id", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Creates a new tag", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Tag> saveTag(@RequestBody Tag tag) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tagService.save(tag));
    }

    @PutMapping
    @Operation(summary = "Updates a existing tag", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Tag> updateTag(@RequestBody Tag tag) {
        return ResponseEntity.ok(tagService.update(tag));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes tag by id", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/post/{id}")
    @Operation(summary = "Gets posts by tag id", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Post>> getPostsByTagId(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.findPostsByTagId(id));
    }
}
