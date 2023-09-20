package com.leave.backend.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import jakarta.persistence.CascadeType;
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
    // @ManyToOne
    // private Organisation organisation;

    @OneToMany(mappedBy = "site",cascade = CascadeType.ALL)
    private List<User> users;

}
