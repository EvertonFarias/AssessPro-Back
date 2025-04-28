package com.example.inovaTest.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private LocalDate dateEvaluation; // data da avaliação
    @Column(nullable = false)
    private String description; // descrição da avaliação
    @Column(name = "time_evaluation")
    private Long timeEvaluation;
    @Column(name = "evaluation_score") // nota que vale a avaliação
    private Integer evaluationScore;
    @Column(name = "evaluation_status") //status da avaliação
    private String evaluationStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "evaluation_participants",
        joinColumns = @JoinColumn(name = "evaluation_id"),
        inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<ProfileModel> participants;
    @OneToMany(mappedBy = "evaluation") 
    private List<QuestionModel> questions; 
    


}