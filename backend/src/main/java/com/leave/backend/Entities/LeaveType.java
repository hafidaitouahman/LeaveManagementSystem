package com.leave.backend.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="leavetypes")

public class LeaveType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;

    private String name;

   
    private boolean approbation;

    private String soustraction;

    // @OneToMany(mappedBy="leaveType")
    // private List<LeaveRequest> leaveRequests;
    
}
