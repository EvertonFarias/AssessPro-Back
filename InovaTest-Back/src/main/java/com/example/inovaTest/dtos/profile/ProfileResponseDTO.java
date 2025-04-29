package com.example.inovaTest.dtos.profile;

import java.util.UUID;

import com.example.inovaTest.models.ProfileModel;

public record ProfileResponseDTO(
    UUID id,
    UUID userId,
    String name,
    String phoneNumber,
    String address,
    String city,
    String state,
    String country
) {
    public ProfileResponseDTO(ProfileModel profile) {
        this(
            profile.getId(),
            profile.getUser().getId(),
            profile.getName(),
            profile.getPhoneNumber(),
            profile.getAddress(),
            profile.getCity(),
            profile.getState(),
            profile.getCountry()
        );
    }
}
