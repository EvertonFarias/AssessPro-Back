package com.example.inovaTest.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "profiles")
public class ProfileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    private String name;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String country;
    @OneToMany(mappedBy = "responsible")
    private List<EvaluationModel> evaluationsResponsible = new ArrayList<>();



    public ProfileModel(UserModel user) {
        this.user = user;
    }

    public ProfileModel(String name, String phoneNumber, String address, String city, String state, String country) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
    }
    
}
