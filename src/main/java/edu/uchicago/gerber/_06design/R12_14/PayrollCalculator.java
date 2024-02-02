package edu.uchicago.gerber._06design.R12_14;

public class PayrollCalculator {
    private static final double OVERTIME_RATE = 1.5;
    private static final int REGULAR_HOURS = 40;
    public double calculatePay(Employee employee){
        double hoursWorked = employee.getHoursWorked();
        double hourlyRate = employee.getHourlyRate();
        if(hoursWorked <= REGULAR_HOURS){
            return hourlyRate * hoursWorked;
        }else{
            double regularPay = hourlyRate * REGULAR_HOURS;
            double overtimeHours = hoursWorked - REGULAR_HOURS;
            double overtimePay = overtimeHours * hourlyRate * OVERTIME_RATE;
            return regularPay + overtimePay;
        }
    }
}
