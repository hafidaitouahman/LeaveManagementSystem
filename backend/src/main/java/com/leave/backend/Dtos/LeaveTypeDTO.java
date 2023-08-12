package com.leave.backend.Dtos;

import lombok.Data;

@Data
public class LeaveTypeDTO {
    
    private int id;

    private String name;

   
    private boolean approbation;

    private String soustraction;

}
