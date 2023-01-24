package com.blog.api;

import com.blog.entity.Category;
import com.blog.entity.Contact;
import com.blog.service.CategoryService;
import com.blog.service.ContactService;
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
@RequestMapping("/api/v1/contacts")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    @Operation(summary = "Get all categories", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<List<Contact>> getAllCategories() {
        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets contact by id", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        return ResponseEntity.ok(contactService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Creates a new contact", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Contact> saveContact(@RequestBody Contact contact) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contact));
    }

    @PutMapping
    @Operation(summary = "Updates a existing contact", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.update(contact));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes contact by id", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
