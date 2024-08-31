package com.Model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

public class Visitor {
    private String idVisitor;
    private String userId;
    private String name;
    private String purpose;
    private Time arrivalTime;
    private Time departureTime;
    private Date date;
    private boolean approved;

    // Getters and Setters
    public String getIdVisitor() {
        return idVisitor;
    }

    public void setIdVisitor(String idVisitor) {
        this.idVisitor = idVisitor;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	} 
}
