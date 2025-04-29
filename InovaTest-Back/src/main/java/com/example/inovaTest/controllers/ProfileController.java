package com.example.inovaTest.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.inovaTest.dtos.profile.ProfileResponseDTO;
import com.example.inovaTest.dtos.profile.UpdateProfileRequestDTO;
import com.example.inovaTest.models.ProfileModel;
import com.example.inovaTest.models.UserModel;
import com.example.inovaTest.repositories.ProfileRepository;
import com.example.inovaTest.repositories.UserRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import java.util.function.Consumer;

@RequestMapping("/profile")
@RestController
public class ProfileController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileRepository repository;


@GetMapping("/{userId}")
public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable UUID userId) {
    UserModel user = this.userRepository.findById(userId).orElse(null);
    if (user == null) {
        return ResponseEntity.notFound().build();
    }
    ProfileModel profile = this.repository.findByUser(user);
    if (profile == null) {
        return ResponseEntity.notFound().build();
    }
    ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO(profile);
    return ResponseEntity.ok(profileResponseDTO);
}
@PutMapping("/{userId}")
public ResponseEntity<ProfileResponseDTO> updateProfile(@PathVariable UUID userId, @Valid @RequestBody UpdateProfileRequestDTO request) {

    UserModel user = this.userRepository.findById(userId).orElse(null);
    if (user == null) {
        return ResponseEntity.notFound().build();
    }

    ProfileModel profile = this.repository.findByUser(user);
    if (profile == null) {
        return ResponseEntity.notFound().build();
    }

    updateField(request.name(), profile::setName);
    updateField(request.phoneNumber(), profile::setPhoneNumber);
    updateField(request.address(), profile::setAddress);
    updateField(request.city(), profile::setCity);
    updateField(request.state(), profile::setState);
    updateField(request.country(), profile::setCountry);

    repository.save(profile);

    ProfileResponseDTO responseDTO = new ProfileResponseDTO(profile);
    return ResponseEntity.ok(responseDTO);
}
// este método me ajuda a evitar a repetição de código
// ele verifica se o campo é vazio ou nullo e se não for, ele atualiza o campo
private void updateField(String newValue, Consumer<String> setter) {
    if (newValue != null && !newValue.isEmpty()) {
        setter.accept(newValue);
    }
}



    
    
}
