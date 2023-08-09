package com.leave.backend.Entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.leave.backend.Entities.Enumeration.Status;

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
    private Status status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creDate;
    private double duration;
    private String Comment;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private LeaveType leaveType;

    
}
