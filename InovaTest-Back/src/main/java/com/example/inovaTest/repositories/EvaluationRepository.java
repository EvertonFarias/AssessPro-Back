package com.example.inovaTest.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inovaTest.models.EvaluationModel;

public interface EvaluationRepository extends JpaRepository<EvaluationModel, UUID> {
}

