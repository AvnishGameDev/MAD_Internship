package com.avnishgamedev.academica.models;

import java.util.Date;

public class Reminder {
    private String id;
    private String userId;
    private String title;
    private String description;
    private Date dateTime;
    private boolean isCompleted;
    private Date createdAt;

    public Reminder() {
        // Default constructor required for Firebase
    }

    public Reminder(String userId, String title, String description, Date dateTime) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.isCompleted = false;
        this.createdAt = new Date();
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDateTime() { return dateTime; }
    public void setDateTime(Date dateTime) { this.dateTime = dateTime; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
