package com.Model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

public class Visitor {
    private String idVisitor;
    private String userId;
    private String name;
    private String purpose;
    private String arrivalTime;
    private String departureTime;
    private String date;
    private String dep_date;
    private String approved;
    private String contactNo;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String time) {
        this.arrivalTime = time;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String time) {
        this.departureTime = time;
    }

	public String isApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}
	

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getDep_date() {
		return dep_date;
	}

	public void setDep_date(String dep_date) {
		this.dep_date = dep_date;
	} 
}
