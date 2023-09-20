package com.leave.backend.Entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.leave.backend.Enumeration.Status;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
   
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Check-in date must not be null")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Check-in date must not be null")
    private LocalDate endDate;
    private Status status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Check-out date must be in the future")
    private LocalDate creDate;
    private double duration;
    private String Comment;
   @ManyToMany
    @JoinTable(
        name = "leave_request_replacement",
        joinColumns = @JoinColumn(name = "leave_request_id"),
        inverseJoinColumns = @JoinColumn(name = "replacement_id")
    )
    private Set<User> replacements = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "approbateur_id")
    private User approver;
    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;
    @ManyToOne
    @JoinColumn(name = "user_id") 
    private User user;
    private double plannedDays;

    
}
