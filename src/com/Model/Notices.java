package com.Model;

import java.sql.Date;

public class Notices {
    private String idNotices;
    private String title;
    private String message;
    private Date date;

    // Getters and Setters
    public String getIdNotices() {
        return idNotices;
    }

    public void setIdNotices(String string) {
        this.idNotices = string;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}

