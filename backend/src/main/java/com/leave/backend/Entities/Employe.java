package com.leave.backend.Entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hirDate;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getHirDate() {
        return hirDate;
    }
    public void setHirDate(Date hirDate) {
        this.hirDate = hirDate;
    }
    @Override
    public String toString() {
        return "Employe [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", hirDate="
                + hirDate + "]";
    }
    public Employe() {
    }
    public Employe(int id, String name, String email, String password, Date hirDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.hirDate = hirDate;
    }

    
}
