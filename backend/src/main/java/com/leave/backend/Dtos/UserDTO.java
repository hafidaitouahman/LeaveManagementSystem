package com.leave.backend.Dtos;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private List<RoleDTO> roles;
    private Date hireDate;
    private int teamId;
    private int departementId;
    private int siteId;
    private boolean active;

}
