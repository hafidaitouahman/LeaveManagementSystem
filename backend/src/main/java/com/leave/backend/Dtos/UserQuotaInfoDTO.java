package com.leave.backend.Dtos;

import lombok.Data;

@Data
public class UserQuotaInfoDTO {
    private int year;
    private double quota;
    private double residuel;

    public UserQuotaInfoDTO(int year, double quota, double residuel) {
        this.year = year;
        this.quota = quota;
        this.residuel = residuel;
    }

    // Getters et Setters
}

