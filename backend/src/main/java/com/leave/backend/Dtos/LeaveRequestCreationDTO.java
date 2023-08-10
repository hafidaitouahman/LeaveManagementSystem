package com.leave.backend.Dtos;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class LeaveRequestCreationDTO {
    private LeaveTypeDTO leaveType;
    private Date startDate;
    private Date endDate;
    private RhDTO approver;
    private RemplacantDTO remplacant;
    private EmployeDTO employe;
    private String comment;


}
