package com.leave.backend.Entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.leave.backend.Enumeration.Status;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @NotNull(message = "Check-in date must not be null")
    private Date startDate;
    @NotNull(message = "Check-in date must not be null")
    private Date endDate;
    private Status status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Check-out date must be in the future")
    private Date creDate;
    private double duration;
    private String Comment;
    @ManyToOne
    @JoinColumn(name = "remplacant_id")
    private User remplacant;
    @ManyToOne
    private User employe;
    @ManyToOne
    private LeaveType leaveType;

    
}
