package com.userservices.userservices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // name validation + DB constraint
    @NotBlank(message = "Name is required")
    @Column(nullable = false, length = 50)
    private String name;

    // email validation + unique
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    // phone validation
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    @Column(nullable = false, length = 10)
    private String phone;

    // age validation
    @Min(value = 1, message = "Age must be greater than 0")
    @Max(value = 120, message = "Age must be less than 120")
    @Column(nullable = false)
    private Integer age;
}
