package edu.uchicago.gerber._04interfaces.P0_3.P9_6;

public class Daily extends Appointment{

    public Daily(String description, int year, int month, int day) {
        super(description, year, month, day);
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        return (this.year < year) ||
                (this.year == year && this.month < month) ||
                (this.year == year && this.month == month && this.day <= day);
    }

}
