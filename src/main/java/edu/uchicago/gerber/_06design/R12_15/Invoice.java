package edu.uchicago.gerber._06design.R12_15;
import edu.uchicago.gerber._06design.R12_14.Paycheck;

import java.util.*;
// Invoice class to represent an invoice
public class Invoice {
    Address billingAddress;
    Address shippingAddress;
    Customer customer;
    List<Item> itemList;
    List<Payment> payments;

    // Constructor, getters, and setters are not shown for brevity

    //Method to calculate the total amount due
    double getTotalAmountDue(){
        double total = 0;
        for(Item item: itemList){
            total += item.product.price*item.quantity;
        }
        for(Payment payment:payments){
            total -= payment.amount;
        }
        return total;
    }

    // Method to generate a summary of the invoice
    void generateSummary() {
        // Implementation of summary generation
        // It could print out the details of the invoice, for instance
    }


}
