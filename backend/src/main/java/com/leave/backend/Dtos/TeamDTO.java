package com.leave.backend.Dtos;
import java.util.List;

import lombok.Data;

@Data
public class TeamDTO {
    private int id; 
    private String name;
    private List<UserDTO> users;

}
