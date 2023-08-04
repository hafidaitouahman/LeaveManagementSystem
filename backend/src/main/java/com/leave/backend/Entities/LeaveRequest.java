package com.leave.backend.Entities;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   
    private int id;
    private Date startDate;
    private Date endDate;
    private String status;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LeaveRequest() {
    }
    public LeaveRequest(int id, Date startDate, Date endDate, String status) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }
    @Override
    public String toString() {
        return "LeaveRequest [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status
                + "]";
    }
    
}
