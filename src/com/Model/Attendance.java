package com.Model;

import java.sql.Date;

public class Attendance {
	private String idAttendance;
	private String userId;
	private String status;
	private Date date;

	// Getters and Setters
	public String getIdAttendance() {
		return idAttendance;
	}

	public void setIdAttendance(String idAttendance) {
		this.idAttendance = idAttendance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
