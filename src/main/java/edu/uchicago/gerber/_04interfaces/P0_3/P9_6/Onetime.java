package edu.uchicago.gerber._04interfaces.P0_3.P9_6;

public class Onetime extends Appointment{

    public Onetime(String description, int year, int month, int day) {
        super(description, year, month, day);
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        return this.year == year && this.month == month && this.day == day;
    }


}
