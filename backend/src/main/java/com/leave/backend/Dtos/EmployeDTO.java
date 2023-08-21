package com.leave.backend.Dtos;
import java.util.Date;
import java.util.List;


import lombok.Data;

@Data
public class EmployeDTO {
    private int id;
    private String name;
    private String email;
    private String password;
      private Date hirDate;
    private String role;
    private String teams;
}
