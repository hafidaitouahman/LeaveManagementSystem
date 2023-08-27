// package com.leave.backend.Entities;

// import java.util.Date;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;

// import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
// import org.springframework.format.annotation.DateTimeFormat;

// import com.fasterxml.jackson.annotation.JsonProperty;

// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.ToString;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @ToString
// @Table(name="Employes")
// @Builder

// public class Employe {
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private int id;
//     private String name;
//     @Email(message = "Invalid email format")
//     @NotBlank(message = "Email is required")
//     private String email;
//     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//     private String password;
//     @DateTimeFormat(pattern = "yyyy-MM-dd")
//     private Date hirDate;
//     // private Role role;
//   	@ManyToMany(fetch = FetchType.EAGER)
// 	@JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "cust_id", referencedColumnName = "id"),
// 	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") )
// 	Set<Role> roles = new HashSet<Role>();

//     @ManyToOne
//     private Organisation organisation;
//     @OneToMany(mappedBy="employe")
//     private List<LeaveRequest> leaveRequests;
//     @ManyToOne
//     private Team teams;
//     @OneToMany
//     private List<LeaveQuota> leaveQuotas;

// 	public Set<Role> getRole() {
// 		return roles;
// 	}

// 	public void setRole(Role role) {
// 		this.roles.add(role);
// 	}
    

    
// }
