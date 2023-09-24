package com.leave.backend.Dtos;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class AddUserRequest {
    private String username;
    private String email;
    private String password;
    private String pays;
    private int departementId;
    private int teamId;
    private int siteId;
     @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date hirDate;
      private Set<String> role;
      private Double quota;
    private Double residuel;


    // Getters et setters
}
