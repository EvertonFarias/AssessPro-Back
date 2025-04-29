package com.example.inovaTest.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inovaTest.dtos.auth.AuthenticationDTO;
import com.example.inovaTest.dtos.auth.LoginResponseDTO;
import com.example.inovaTest.dtos.auth.RegisterDTO;
import com.example.inovaTest.dtos.user.UserResponseDTO;
import com.example.inovaTest.exceptions.ConflictException;
import com.example.inovaTest.infra.security.TokenService;
import com.example.inovaTest.models.UserModel;
import com.example.inovaTest.services.AuthService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
        try {
        UserModel newUser = authService.registerUser(data);
        UserResponseDTO responseDTO = new UserResponseDTO(
            newUser.getId(),
            newUser.getLogin(),
            newUser.getEmail(),
            newUser.getRole()
        );
        
        return ResponseEntity.ok(responseDTO); // Retorna o DTO com os dados específicos
        } catch (ConflictException e) {
            String errorMessage = e.getMessage(); 
            return ResponseEntity.badRequest().body(errorMessage);
        } catch (Exception e) { 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request.");
        }
    }
}
