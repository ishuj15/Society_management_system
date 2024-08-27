package com.Model;
import java.sql.Date;

public class Complaint {
    private static int idComplaint;
    private int userId;
    private String description;
    private Date date;
    private String status;

    // Getters and Setters
    public static int getIdComplaint() {
        return idComplaint;
    }

    public void setIdComplaint(int idComplaint) {
        this.idComplaint = idComplaint;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
