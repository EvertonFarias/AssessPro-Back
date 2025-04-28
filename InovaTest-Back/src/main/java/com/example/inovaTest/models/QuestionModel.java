package com.example.inovaTest.models;

import java.util.List;
import java.util.UUID;

import com.example.inovaTest.enums.QuestionTypeEnum;
import com.example.inovaTest.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String question_description; //descrição da questão
    @ManyToOne
    @JoinColumn(name = "evaluation_id", nullable = false)
    private EvaluationModel evaluation; // avaliação a qual a questão pertence


    @Enumerated(EnumType.STRING)
    private QuestionTypeEnum question_type; //tipo da questão (alternativa, dissertativa, etc)
    private String question_answer; //resposta correta

    // @OneToMany(mappedBy = "question")
    // private List<AlternativeQuestionModel> alternatives;//alternativas da questão (se houver)
     
    private String text_response; // resposta para questões dissertativas


}
