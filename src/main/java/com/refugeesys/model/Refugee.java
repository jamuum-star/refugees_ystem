package com.refugeesys.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "refugee_tb")
public class Refugee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // full name field with validation constraints
    @NotEmpty(message = "Full Name is required")
    @Size(min = 3, max = 50, message = "Full Name must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Full Name must contain only letters and spaces")
    @Column(nullable = true)
    private String fullname;
    // Family Size field with validation constraints
    @NotEmpty(message = "Family Size is required")
    @Min(value = 1, message = "Family Size must be at least 1")
    @Column(nullable = true)
    private String familysize;
    // Phone field with validation constraints (must be unique and 9 digits)
    @NotEmpty(message = "Contact is required")
    @Pattern(regexp = "\\d{9}", message = "Contact must be 9 digits")
    @Column(unique = true, length = 20)
    private String phone;
    // Address field with validation constraints
    @NotEmpty(message = "Address is required")
    @Column(nullable = true)
    private String address;
    // Status field with validation constraints
    @NotEmpty(message = "Status is required")
    @Column(nullable = true)
    private String status;
}
