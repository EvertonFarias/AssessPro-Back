package com.example.inovaTest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inovaTest.dtos.evaluation.EvaluationRequestDTO;
import com.example.inovaTest.dtos.evaluation.EvaluationResponseDTO;
import com.example.inovaTest.models.EvaluationModel;
import com.example.inovaTest.models.ProfileModel;
import com.example.inovaTest.repositories.EvaluationRepository;
import com.example.inovaTest.repositories.ProfileRepository;
import com.example.inovaTest.utils.CodeGenerator;

import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    private final EvaluationRepository evaluationRepository;
    private final ProfileRepository profileRepository;
    public EvaluationController(EvaluationRepository evaluationRepository, ProfileRepository profileRepository) {
        this.evaluationRepository = evaluationRepository;
        this.profileRepository = profileRepository;
    }
    @GetMapping("/{id}")
    public ResponseEntity<EvaluationResponseDTO> getEvaluationById(@PathVariable UUID id) {
        return evaluationRepository.findById(id)
            .map(evaluation -> {
                EvaluationResponseDTO responseDTO = new EvaluationResponseDTO(
                    evaluation.getId(),
                    evaluation.getResponsible().getId(),
                    evaluation.getTitle(),
                    evaluation.getFinalDateEvaluation(),
                    evaluation.getDescription(),
                    evaluation.getEvaluationScore(),
                    evaluation.getEvaluationCode(),
                    evaluation.getCreatedDate()
                );
                return ResponseEntity.ok(responseDTO);
            })
            .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<EvaluationResponseDTO> createEvaluation(@RequestBody @Valid EvaluationRequestDTO body) {
        ProfileModel profile = profileRepository.findById(body.getResponsibleProfileId())
                                                .orElseThrow(() -> new RuntimeException("Profile not found"));

        // Gera código único da avaliação
        String code;
        do {
            code = CodeGenerator.generateCode();
        } while (evaluationRepository.existsByEvaluationCode(code));

        EvaluationModel evaluation = new EvaluationModel(body);
        evaluation.setResponsible(profile);
        evaluation.setEvaluationCode(code);
    

        evaluationRepository.save(evaluation);
        EvaluationResponseDTO responseDTO = new EvaluationResponseDTO(
            evaluation.getId(),
            evaluation.getResponsible().getId(),
                    evaluation.getTitle(),
                    evaluation.getFinalDateEvaluation(),
                    evaluation.getDescription(),
                    evaluation.getEvaluationScore(),
                    evaluation.getEvaluationCode(),
                    evaluation.getCreatedDate());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    
    
}
