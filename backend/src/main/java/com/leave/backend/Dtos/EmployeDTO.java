package com.leave.backend.Dtos;
import java.util.List;

import lombok.Data;

@Data
public class EmployeDTO {
    private int id;
    private String name;
    private TeamDTO teamDTO;
}
