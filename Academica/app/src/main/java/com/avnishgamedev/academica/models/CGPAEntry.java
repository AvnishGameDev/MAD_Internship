package com.avnishgamedev.academica.models;

import java.util.List;

public class CGPAEntry {
    private String id;
    private String userId;
    private String semesterName;
    private List<SubjectGrade> subjects;
    private double gpa;
    private double totalCredits;

    public CGPAEntry() {
        // Default constructor required for Firebase
    }

    public CGPAEntry(String userId, String semesterName, List<SubjectGrade> subjects, double gpa, double totalCredits) {
        this.userId = userId;
        this.semesterName = semesterName;
        this.subjects = subjects;
        this.gpa = gpa;
        this.totalCredits = totalCredits;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getSemesterName() { return semesterName; }
    public void setSemesterName(String semesterName) { this.semesterName = semesterName; }

    public List<SubjectGrade> getSubjects() { return subjects; }
    public void setSubjects(List<SubjectGrade> subjects) { this.subjects = subjects; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    public double getTotalCredits() { return totalCredits; }
    public void setTotalCredits(double totalCredits) { this.totalCredits = totalCredits; }

    public static class SubjectGrade {
        private String subjectName;
        private double credits;
        private String grade; // A+, A, B+, B, C+, C, D, F
        private double gradePoint;

        public SubjectGrade() {}

        public SubjectGrade(String subjectName, double credits, String grade, double gradePoint) {
            this.subjectName = subjectName;
            this.credits = credits;
            this.grade = grade;
            this.gradePoint = gradePoint;
        }

        // Getters and setters
        public String getSubjectName() { return subjectName; }
        public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

        public double getCredits() { return credits; }
        public void setCredits(double credits) { this.credits = credits; }

        public String getGrade() { return grade; }
        public void setGrade(String grade) { this.grade = grade; }

        public double getGradePoint() { return gradePoint; }
        public void setGradePoint(double gradePoint) { this.gradePoint = gradePoint; }
    }
}
