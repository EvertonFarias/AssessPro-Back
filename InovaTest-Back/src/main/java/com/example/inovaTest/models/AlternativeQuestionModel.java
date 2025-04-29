package com.example.inovaTest.models;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alternative_questions")
public class AlternativeQuestionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionModel question; // A questão à qual essa alternativa pertence

    private String description; // Descrição da alternativa

    private Boolean isCorrect; // Se for a alternativa correta
}
