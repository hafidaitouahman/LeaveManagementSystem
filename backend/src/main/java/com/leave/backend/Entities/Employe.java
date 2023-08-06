package com.leave.backend.Entities;

import java.util.Date;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.format.annotation.DateTimeFormat;

import com.leave.backend.Entities.Enumeration.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="Employes")

public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hirDate;
    private Role role;
    @ManyToOne
    private Organisation organisation;
    @OneToMany(mappedBy="employe")
    private List<LeaveRequest> leaveRequests;
    @ManyToOne
    private Team team;
    @OneToMany(mappedBy="employe")
    private List<LeaveQuota> leaveQuotas;


    

    
}
