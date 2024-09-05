package com.Model;

import java.sql.Date;

public class Complaint {
	private String idComplaint;
	private String userId;
	private String description;
	private Date date;
	private String status;

	// Getters and Setters
	public String getIdComplaint() {
		return idComplaint;
	}

	public void setIdComplaint(String idComplaint) {
		this.idComplaint = idComplaint;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId2) {
		this.userId = userId2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
