package com.example.inovaTest.models;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data   
@Entity
@Table(name = "evaluations")
public class EvaluationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "responsible_evaluation", nullable = false)
    ProfileModel responsible; // responsável pela avaliação
    private Date dateEvaluation; // data da avaliação
    @Column(nullable = false)
    private String description; // descrição da avaliação
    @Column(name = "time_evaluation")
    private Duration timeEvaluation;
    @Column(name = "evaluation_score") // nota que vale a avaliação
    private Integer evaluationScore;
    @Column(name = "evaluation_status") //status da avaliação
    private String evaluationStatus;
    @ManyToMany
    @JoinTable(
        name = "evaluation_participants", // aqui to criando a tabela de relacionamento
        joinColumns = @JoinColumn(name = "evaluation_id"),
        inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<ProfileModel> participants;

    public EvaluationModel(ProfileModel responsible, Date dateEvaluation, String description, Duration timeEvaluation, Integer evaluationScore, String evaluationStatus) {
        this.responsible = responsible;
        this.dateEvaluation = dateEvaluation;
        this.description = description;
        this.timeEvaluation = timeEvaluation;
        this.evaluationScore = evaluationScore;
        this.evaluationStatus = evaluationStatus;
    }


}