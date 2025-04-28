package com.example.inovaTest.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inovaTest.models.ProfileModel;

public interface UserProfileRepository extends JpaRepository<ProfileModel, UUID> {


}
