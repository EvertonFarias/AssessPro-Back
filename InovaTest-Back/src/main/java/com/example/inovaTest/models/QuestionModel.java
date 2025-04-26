package com.example.inovaTest.models;

import com.example.inovaTest.enums.QuestionTypeEnum;
import com.example.inovaTest.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "questions")
public class QuestionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question_description; //descrição da questão

    @Enumerated(EnumType.STRING)
    private QuestionTypeEnum question_type; //tipo da questão (alternativa, dissertativa, etc)
    private String question_answer; //resposta correta


}
