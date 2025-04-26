package com.AssessPro.service;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.AssessPro.repository.UserRepository;
import com.AssessPro.model.UserModel;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    public UserModel registerUser(String username, String password, String email) {
        String encodedPassword = passwordEncoder.encode(password);
        UserModel user = new UserModel(username, encodedPassword, email);
        return userRepository.save(user);
    }
    public UserModel getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    
}
