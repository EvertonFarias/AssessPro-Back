package com.example.inovaTest.dtos.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProfileRequestDTO(
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    String name,

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number must be at most 20 characters")
    String phoneNumber,

    @Size(max = 255, message = "Address must be at most 255 characters")
    String address,

    @Size(max = 100, message = "City must be at most 100 characters")
    String city,

    @Size(max = 50, message = "State must be at most 50 characters")
    String state,

    @Size(max = 50, message = "Country must be at most 50 characters")
    String country
) {}
