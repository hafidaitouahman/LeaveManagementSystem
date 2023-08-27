package com.leave.backend.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name="teams")

public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private int id;

  
    private String name;
    // @OneToMany
    // private List<Employe> employes;
    @ManyToOne
    private Organisation organisation;

   
}
