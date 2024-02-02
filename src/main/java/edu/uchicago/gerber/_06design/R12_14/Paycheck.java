package edu.uchicago.gerber._06design.R12_14;
import java.util.*;
public class Paycheck {
    private double amount;
    private Date dateIssued;

    public Paycheck(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return this.amount;
    }
    public  Date getDateIssued(){
        return this.dateIssued;
    }
    public void setAmount(double amount){
        this.amount=amount;

    }
    public void setDateIssued(Date date){
        this.dateIssued = date;
    }
}
