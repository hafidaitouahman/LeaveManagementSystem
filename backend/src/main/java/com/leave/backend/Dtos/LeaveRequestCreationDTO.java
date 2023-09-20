package com.leave.backend.Dtos;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.leave.backend.Enumeration.Status;

import lombok.Data;
@Data

public class LeaveRequestCreationDTO {
    private Long leaveTypeId;
    private Long approverId;
    private Set<Long> replacementIds;
    private LocalDate startDate;
    private LocalDate endDate;
    private String comment;
    private Status status = Status.En_Attente; // Default value is En Attente


    // Getter and Setter methods
}

// public class LeaveRequestCreationDTO {
//     private String leaveType;
//     private Date startDate;
//     private Date endDate;
//     private String approver;
//     private String remplacant;
//     private String comment;


// }
