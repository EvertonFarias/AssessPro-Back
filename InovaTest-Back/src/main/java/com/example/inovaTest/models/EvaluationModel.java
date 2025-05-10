package com.example.inovaTest.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.inovaTest.dtos.evaluation.EvaluationRequestDTO;
import com.example.inovaTest.enums.EvaluationStatusEnum;

@NoArgsConstructor
@AllArgsConstructor
@Data   
@Entity(name = "evaluations")
@Table(name = "evaluations")
public class EvaluationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "responsible_evaluation", nullable = false)
    private ProfileModel responsible; // responsável pela avaliação

    @Column(nullable = false)
    private String title; 

    @Column(name = "evaluation_code", nullable = false, unique = true)
    private String evaluationCode; // código da avaliação 


    @Column(nullable = false)
    private LocalDateTime finalDateEvaluation; // prazo maximo para fazer a avaliação

    @Column(nullable = false)
    private String description; // descrição da avaliação


    @Column(name = "evaluation_score") // nota que vale a avaliação
    private Integer evaluationScore;

    @Column(name = "evaluation_status") //status da avaliação
    @Enumerated(EnumType.STRING)
    private EvaluationStatusEnum evaluationStatus;
    
    @Column(name = "max_participants") 
    private int maxParticipants; // número máximo de participantes

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "evaluation_participants",
        joinColumns = @JoinColumn(name = "evaluation_id"),
        inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<ProfileModel> participants;
    @OneToMany(mappedBy = "evaluation") 
    private List<QuestionModel> questions; 


    private LocalDateTime createdDate = LocalDateTime.now();

    public EvaluationModel(ProfileModel responsible, String title, String evaluationCode, LocalDateTime finalDateEvaluation,
            String description, Integer evaluationScore) {
        this.responsible = responsible;
        this.title = title;
        this.evaluationCode = evaluationCode;
        this.finalDateEvaluation = finalDateEvaluation;
        this.description = description;
        this.evaluationScore = evaluationScore;
        this.evaluationStatus = EvaluationStatusEnum.PENDING; // status padrão
        this.maxParticipants = 20; // valor padrão para usuário comum

    }

    public EvaluationModel(EvaluationRequestDTO body) {
        this.title = body.title();
        this.finalDateEvaluation = body.finalDateEvaluation();
        this.description = body.description();
        this.evaluationScore = body.evaluationScore();
        this.evaluationStatus = EvaluationStatusEnum.PENDING; // status padrão
        this.maxParticipants = 20; // valor padrão para usuário comum
    }

    


}