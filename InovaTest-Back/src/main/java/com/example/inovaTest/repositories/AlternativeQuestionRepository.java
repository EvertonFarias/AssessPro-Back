package com.example.inovaTest.repositories;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inovaTest.models.AlternativeQuestionModel;

public interface AlternativeQuestionRepository extends JpaRepository<AlternativeQuestionModel, UUID> {
    
}
