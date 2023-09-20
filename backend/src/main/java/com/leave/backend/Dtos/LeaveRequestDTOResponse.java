package com.leave.backend.Dtos;

import java.time.LocalDate;
import java.util.List;

import com.leave.backend.Enumeration.Status;

import lombok.Data;
@Data
public class LeaveRequestDTOResponse {
    private LocalDate from;
    private LocalDate to;
    private double duration;
    private double plannedDays;
    private String requesterUsername; // Nom d'utilisateur de l'utilisateur qui a fait la demande
    private String approverUsername; // Nom d'utilisateur de l'approbateur
    private List<String> replacementUsernames; // Noms d'utilisateurs des remplaçants
    private String leaveTypeName; // Nom du type de congé
    private String comment;
    private Status status;


    // Constructors, getters, and setters
}
