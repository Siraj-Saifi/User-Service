package com.userservices.userservices.controller;

import com.userservices.userservices.entity.User;
import com.userservices.userservices.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    // CREATE
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {

        User saved = service.createUser(user);

        return ResponseEntity.status(201).body(saved);
    }

    // READ ALL (Pagination + Sorting)
    @GetMapping
    public ResponseEntity<Page<User>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {

        Page<User> users = service.getAllUsers(page, size, sortBy, direction);

        return ResponseEntity.ok(users);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {

        return ResponseEntity.ok(service.getUserById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable Long id,
            @Valid @RequestBody User user) {

        return ResponseEntity.ok(service.updateUser(id, user));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
