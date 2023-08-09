package com.leave.backend.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   
    private int id;

    private String name;
    @ManyToOne
    private Organisation organisation;

    

}
