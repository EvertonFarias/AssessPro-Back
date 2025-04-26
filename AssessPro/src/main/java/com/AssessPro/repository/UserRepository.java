package com.AssessPro.repository;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.AssessPro.model.UserModel;



@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
        
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByUsernameOrEmail(String username, String email);
    
}
