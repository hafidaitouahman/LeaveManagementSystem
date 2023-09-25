package com.leave.backend.Dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserDetailsResponse {
    private Long id;
    private String username;
    private String email;
    private String pays;
    private String departementName; // Le nom du département de l'utilisateur
    private String teamName;       // Le nom de l'équipe de l'utilisateur
    private String siteName;       // Le nom du site de l'utilisateur
    private boolean active;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hirDate;
    private double quota;
    private double residuel;

    // Getters et setters pour chaque attribut
    // ...
}

