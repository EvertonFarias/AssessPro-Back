package com.example.inovaTest.repositories;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inovaTest.models.QuestionModel;

public interface QuestionRepository extends JpaRepository<QuestionModel, UUID> {
    
}
