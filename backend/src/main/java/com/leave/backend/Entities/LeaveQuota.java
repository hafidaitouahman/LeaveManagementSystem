package com.leave.backend.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class LeaveQuota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   
    private int id;
    private int year;
    private double quota;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public double getQuota() {
        return quota;
    }
    public void setQuota(double quota) {
        this.quota = quota;
    }
    public LeaveQuota() {
    }
    public LeaveQuota(int id, int year, double quota) {
        this.id = id;
        this.year = year;
        this.quota = quota;
    }
    @Override
    public String toString() {
        return "LeaveQuota [id=" + id + ", year=" + year + ", quota=" + quota + "]";
    }

}
