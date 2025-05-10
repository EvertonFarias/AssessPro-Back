package com.example.inovaTest.dtos.evaluation;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class EvaluationResponseDTO {
    private UUID id;
    private UUID responsibleProfileId;
    private String evaluationCode;
    private String title;
    private String description;
    private Integer evaluationScore;
    private LocalDateTime finalDateEvaluation;
    private LocalDateTime createdDate;


    public EvaluationResponseDTO(UUID id,UUID responsibleProfileId, String title, LocalDateTime finalDateEvaluation, 
    String description, Integer evaluationScore, String evaluationCode, LocalDateTime createdDate) 
    {
        this.id = id;
        this.responsibleProfileId = responsibleProfileId;
        this.title = title;
        this.finalDateEvaluation = finalDateEvaluation;
        this.description = description;
        this.evaluationScore = evaluationScore;
        this.evaluationCode = evaluationCode;
        this.createdDate = createdDate;
    }


}