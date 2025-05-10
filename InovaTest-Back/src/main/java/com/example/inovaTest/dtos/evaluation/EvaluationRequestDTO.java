package com.example.inovaTest.dtos.evaluation;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EvaluationRequestDTO(
    @NotNull UUID responsibleProfileId,
    @NotBlank String title,
    @NotNull LocalDateTime finalDateEvaluation,
    @NotBlank String description,
    @NotNull Integer evaluationScore
) { 
    public UUID getResponsibleProfileId(){
        return responsibleProfileId;
    }
}
