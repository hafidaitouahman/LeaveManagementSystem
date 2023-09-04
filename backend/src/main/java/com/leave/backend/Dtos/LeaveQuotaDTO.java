package com.leave.backend.Dtos;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class LeaveQuotaDTO {
    private int id;
    private int year;
    private double quota;
    private Set<UserDTO> users;

    // Getter et setter pour id, year, et quota
    // Ajoutez la méthode setter pour la liste d'utilisateurs

    public void setUsers(Set<UserDTO> list) {
        this.users = list;
    }
}
