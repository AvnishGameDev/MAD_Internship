package com.avnishgamedev.academica.models;

public class TimetableEntry {
    private String id;
    private String userId;
    private String subjectName;
    private String professorName;
    private String classroom;
    private String dayOfWeek; // MONDAY, TUESDAY, etc.
    private String startTime; // HH:mm format
    private String endTime; // HH:mm format

    public TimetableEntry() {
        // Default constructor required for Firebase
    }

    public TimetableEntry(String userId, String subjectName, String professorName, 
                         String classroom, String dayOfWeek, String startTime, String endTime) {
        this.userId = userId;
        this.subjectName = subjectName;
        this.professorName = professorName;
        this.classroom = classroom;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getProfessorName() { return professorName; }
    public void setProfessorName(String professorName) { this.professorName = professorName; }

    public String getClassroom() { return classroom; }
    public void setClassroom(String classroom) { this.classroom = classroom; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
}
