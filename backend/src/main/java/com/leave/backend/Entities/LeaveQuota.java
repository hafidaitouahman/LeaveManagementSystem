package com.leave.backend.Entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "quotas")
public class LeaveQuota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private double quota;
    private String pays;

    // @ManyToMany(cascade = {
    //     CascadeType.ALL
    // })
    //     @JoinTable(
    //     name = "user_quota",
    //     joinColumns = @JoinColumn(name = "quota_id"),
    //     inverseJoinColumns = @JoinColumn(name = "user_id")
    // )
    // private Set<User> users = new HashSet<>(); 
    // public Set<User> getUsers() {
    //     return users;
    //   }
    
    
    //   public void setUsers(Set<User> users) {
    //     this.users = users;
    //   }
    
}
