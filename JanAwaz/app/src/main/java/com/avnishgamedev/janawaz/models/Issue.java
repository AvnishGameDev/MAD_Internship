package com.avnishgamedev.janawaz.models;

import java.util.Date;

public class Issue {
    private String id;
    private String title;
    private String description;
    private String category;
    private String location;
    private String contactInfo;
    private String userId;
    private String userEmail;
    private String userName;
    private String status; // Pending, In Queue, Fixing, Resolved
    private Date submittedAt;
    private Date updatedAt;
    private double latitude;
    private double longitude;

    public Issue() {
        // Default constructor required for calls to DataSnapshot.getValue(Issue.class)
    }

    public Issue(String title, String description, String category, String location, 
                 String contactInfo, String userId, String userEmail, String userName,
                 double latitude, double longitude) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.location = location;
        this.contactInfo = contactInfo;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.status = "Pending";
        this.submittedAt = new Date();
        this.updatedAt = new Date();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { 
        this.status = status;
        this.updatedAt = new Date();
    }

    public Date getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(Date submittedAt) { this.submittedAt = submittedAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}
