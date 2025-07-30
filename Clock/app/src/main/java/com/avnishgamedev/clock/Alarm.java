package com.avnishgamedev.clock;

public class Alarm {
    private int id = -1;
    private String label;
    private int hour;
    private int minute;
    private boolean isEnabled;

    public Alarm(int id, String label, int hour, int minute, boolean isEnabled) {
        this.id = id;
        this.label = label;
        this.hour = hour;
        this.minute = minute;
        this.isEnabled = isEnabled;
    }

    public Alarm(String label, int hour, int minute, boolean isEnabled) {
        this.label = label;
        this.hour = hour;
        this.minute = minute;
        this.isEnabled = isEnabled;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}
