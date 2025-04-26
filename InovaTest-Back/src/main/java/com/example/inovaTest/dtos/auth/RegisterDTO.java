package com.example.inovaTest.dtos.auth;

import com.example.inovaTest.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role, String email) {
}
