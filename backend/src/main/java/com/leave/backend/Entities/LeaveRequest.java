package com.leave.backend.Entities;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   
    private int id;
    private Date startDate;
    private Date endDate;
    private String status;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private LeaveType leaveType;

    
}
