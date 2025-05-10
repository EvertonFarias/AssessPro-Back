package com.example.inovaTest.repositories;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inovaTest.models.ProfileModel;
import com.example.inovaTest.models.UserModel;






public interface ProfileRepository extends JpaRepository<ProfileModel, UUID> {
    ProfileModel findByUser(UserModel user);

    
}
