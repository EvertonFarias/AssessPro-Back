package com.example.inovaTest.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inovaTest.models.EvaluationModel;
import com.example.inovaTest.models.ProfileModel;

public interface EvaluationRepository extends JpaRepository<EvaluationModel, UUID> {
    EvaluationModel findByEvaluationCode(String evaluationCode); // busca avaliação pelo código
    Boolean existsByEvaluationCode(String evaluationCode); // verifica se o código já existe
    List<EvaluationModel> findByResponsible(ProfileModel responsible);
}

