package com.leave.backend.Dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class LeaveRequestCreationDTO {
    private LeaveTypeDTO type;
    private Date startDate;
    private Date endDate;
    private List<RhDTO> approvers;
    private List<RemplacantDTO> remplacants;
    private List<EmployeDTO> employes;
    private String comment;


}
