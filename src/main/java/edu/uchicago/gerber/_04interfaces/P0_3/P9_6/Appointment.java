package edu.uchicago.gerber._04interfaces.P0_3.P9_6;

public abstract class Appointment {

    protected String description;
    protected int year;
    protected int month;
    protected int day;

    public Appointment(String description, int year, int month, int day) {
        this.description = description;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public abstract boolean occursOn(int year, int month, int day);

    public String getDescription() {
        return description;
    }

}
