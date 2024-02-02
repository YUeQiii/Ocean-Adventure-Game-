package edu.uchicago.gerber._06design.R12_15;

import  java.util.*;

public class Example {
    public static void main(String[] args){
        Address billingAddress = new Address();
        Address shippingAddress = new Address();

        Customer customer = new Customer();
        customer.billingAddress = billingAddress;
        customer.shippingAddress = billingAddress;

        Product product = new Product();

        Item item = new Item();
        item.product = product;
        item.quantity = 3;

        Invoice invoice = new Invoice();
        invoice.customer = customer;
        invoice.billingAddress = billingAddress;
        invoice.shippingAddress = shippingAddress;
        invoice.itemList = Arrays.asList(item);
        invoice.payments = Arrays.asList(new Payment());

        System.out.println("Total Amount Due : "+invoice.getTotalAmountDue());
        invoice.generateSummary();
    }
}
