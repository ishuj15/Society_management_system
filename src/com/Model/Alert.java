package com.Model;

import java.sql.Date;

public class Alert {
    private String idAlert;
    private String message;
    private Date date;
    private String targetRole;

    // Getters and Setters
    public String getIdAlert() {
        return idAlert;
    }

    public void setIdAlert(String idAlert) {
        this.idAlert = idAlert;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

	public String getTargetRole() {
		return targetRole;
	}

	public void setTargetRole(String targetRole) {
		this.targetRole = targetRole;
	}
}

