package com.jarosciak.numberconverter;

public class Appointment {

    // data members
    private String appointmentDescription;
    private String startTime;
    private String endTime;
    private Long durationTime;

    // Set Start Time
    public void setStartTime(String data) {
        startTime = data;
    }

    // Set End Time
    public void setEndTime(String data) {
        endTime = data;
    }

    // Set Appointment Description
    public void setAppointmentDescription(String data) {
        appointmentDescription = data;
    }

    // Set Duration
    public void setDurationTime(Long durationTime) {
        this.durationTime = durationTime;
    }

    // Get Start Time
    public String getStartTime() {
        return startTime;
    }

    // Get End Time
    public String getEndTime() {
        return endTime;
    }

    // Get Duration
    public Long getDurationTime() {
        return durationTime;
    }
}


