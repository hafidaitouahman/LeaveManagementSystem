package com.leave.backend.Entities;

import java.util.List;
//import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="Organisations")

public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   
    private int id;
    private String name;
    private String address;
    private String description;
    @OneToMany(mappedBy="organisation")
    private List<Employe> employes;
    // private List<Team> teams;
    // private List<Departement> departements;
    // private List<Site> sites;

}
