package com.userservices.userservices.repository;

import com.userservices.userservices.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // i will change here List to page of pagination
    Page<User> findByAgeGreaterThan(int age, Pageable pageable);

    // Null-safe user if user not found program cannot crush
    Optional<User> findByEmail(String email);
}
