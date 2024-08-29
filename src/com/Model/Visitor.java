package com.Model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

public class Visitor {
    private int idVisitor;
    private int userId;
    private String name;
    private String purpose;
    private Time arrivalTime;
    private Time departureTime;
    private Date date;

    // Getters and Setters
    public int getIdVisitor() {
        return idVisitor;
    }

    public void setIdVisitor(int idVisitor) {
        this.idVisitor = idVisitor;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time time) {
        this.arrivalTime = time;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time time) {
        this.departureTime = time;
    } 
}
