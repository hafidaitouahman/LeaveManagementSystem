package com.leave.backend.Entities;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany
  @JoinTable(name = "user_roles", 
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

    private boolean active;

 
  @ManyToOne
  @JoinColumn(name = "departement_id")
  private Departement departement;
  
  @ManyToOne
  @JoinColumn(name = "site_id")
  private Site site;
  
  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;


  private String pays;

 @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date hirDate;


  // Ajoutez la relation avec les demandes de congé
  // @OneToMany(mappedBy = "user")
  // private Set<LeaveRequest> leaveRequests = new HashSet<>();


  // @ManyToMany(mappedBy = "users",cascade = { CascadeType.ALL })
  // private Set<LeaveQuota> quotas = new HashSet<>();
  // // public User() {
  // }
  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;

  }
  public User(String username, String email, String password,String pays, Date hirDate) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.pays=pays;
    this.hirDate=hirDate;
  }

  // public User(Date hirDate, Team team, Departement departement, Site site) {
  //   this.hirDate = hirDate;
  //   this.team = team;
  //   this.departement = departement;
  //   this.site = site;
  // }

  // public Long getId() {
  //   return id;
  // }

  // public void setId(Long id) {
  //   this.id = id;
  // }

  // public String getUsername() {
  //   return username;
  // }

  // public void setUsername(String username) {
  //   this.username = username;
  // }

  // public String getEmail() {
  //   return email;
  // }

  // public void setEmail(String email) {
  //   this.email = email;
  // }

  // public String getPassword() {
  //   return password;
  // }

  // public void setPassword(String password) {
  //   this.password = password;
  // }

  // public Set<Role> getRoles() {
  //   return roles;
  // }


  // public void setRoles(Set<Role> roles) {
  //   this.roles = roles;
  // }

  // private boolean active;

  // public boolean isActive() {
  //     return active;
  // }

  // public void setActive(boolean active) {
  //     this.active = active;
  // }

}
