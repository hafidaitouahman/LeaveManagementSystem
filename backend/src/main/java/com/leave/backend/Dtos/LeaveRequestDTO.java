package com.leave.backend.Dtos;
import java.util.Date;


import lombok.Data;

@Data
public class LeaveRequestDTO {
    private String status;
    private String type;
    // type Date ou String ?? 
    private Date creDate;
    private Date startDate;
    private Date endDate;
    private String duration;
    private String durationWork;
    private String remplacant;
    private String approver;
    private String comment;

}
