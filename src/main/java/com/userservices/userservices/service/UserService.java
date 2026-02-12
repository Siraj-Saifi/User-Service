package com.userservices.userservices.service;

import com.userservices.userservices.entity.User;
import com.userservices.userservices.exception.ResourceNotFoundException;
import com.userservices.userservices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;

    // Create
    public User createUser(User user) {
        return repo.save(user);
    }

    // Pagination + Sorting
    public Page<User> getAllUsers(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return repo.findAll(pageable);
    }

    //  Get by id with proper exception
    public User getUserById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + id));
    }

    //  Update
    public User updateUser(Long id, User newUser) {

        User user = getUserById(id);

        user.setName(newUser.getName());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());
        user.setAge(newUser.getAge());

        return repo.save(user);
    }

    //  Delete safely
    public void deleteUser(Long id) {

        User user = getUserById(id); // throws if not found

        repo.delete(user);
    }
}
