package com.example.inovaTest.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.inovaTest.dtos.evaluation.EvaluationResponseDTO;
import com.example.inovaTest.dtos.profile.ProfileResponseDTO;
import com.example.inovaTest.dtos.profile.UpdateProfileRequestDTO;
import com.example.inovaTest.models.EvaluationModel;
import com.example.inovaTest.models.ProfileModel;
import com.example.inovaTest.models.UserModel;
import com.example.inovaTest.repositories.EvaluationRepository;
import com.example.inovaTest.repositories.ProfileRepository;
import com.example.inovaTest.repositories.UserRepository;

import jakarta.validation.Valid;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;



@RequestMapping("/profile")
@RestController
public class ProfileController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProfileRepository repository;
    @Autowired
    EvaluationRepository evaluationRepository;



@GetMapping("/{username}")
public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable String username) {
    UserModel user = this.userRepository.getByLogin(username).orElse(null);
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

@GetMapping("/evaluations/{id}") // id do perfil
public ResponseEntity<List<EvaluationResponseDTO>> getEvaluationsByProfileId(@PathVariable UUID id) {
    Optional<ProfileModel> optionalProfile = repository.findById(id);

    if (optionalProfile.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    ProfileModel profile = optionalProfile.get();
    List<EvaluationModel> evaluations = evaluationRepository.findByResponsible(profile);

    List<EvaluationResponseDTO> dtoList = evaluations.stream().map(evaluation -> 
        new EvaluationResponseDTO(
            evaluation.getId(),
            evaluation.getResponsible().getId(),
            evaluation.getTitle(),
            evaluation.getFinalDateEvaluation(),
            evaluation.getDescription(),
            evaluation.getEvaluationScore(),
            evaluation.getEvaluationCode(),
            evaluation.getCreatedDate()
        )
    ).toList();

    return ResponseEntity.ok(dtoList);
}









private void updateField(String newValue, Consumer<String> setter) {
// este método me ajuda a evitar a repetição de código
// ele verifica se o campo é vazio ou nullo e se não for, ele atualiza o campo
    if (newValue != null && !newValue.isEmpty()) {
        setter.accept(newValue);
    }
}



    
    
}
