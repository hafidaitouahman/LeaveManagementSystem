package com.leave.backend.Dtos;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import lombok.Data;
@Data
public class LeaveRequestCreationDTO {
    private String leaveType;
    private Date startDate;
    private Date endDate;
    private String approver;
    private String remplacant;
    private String comment;


}
