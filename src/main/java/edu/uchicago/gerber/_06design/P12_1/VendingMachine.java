package edu.uchicago.gerber._06design.P12_1;
import java.util.*;
public class VendingMachine {
    private  Map<String,Product> products;
    private int balance;

    public VendingMachine(){
        this.products = new HashMap<>();
        this.balance =0;
    }

    public  void addProduct (Product product){
        products.put(product.getName(), product);
    }

    public String selectProduct(String productName, int moneyInserted){
        Product product = products.get(productName);
        if(product == null){
            return "Product not found";
        }
        if(!product.isAvailable()){
            return "Product is sold out";
        }
        if (moneyInserted < product.getPrice()) {
            return "Insufficient money inserted";
        }

        product.dispense();
        balance += product.getPrice();
        return "Dispensed" + product.getName();

    }
    public  void restockProduct(String productName, int amount){
        Product product = products.get(productName);
        if(product != null){
            product.restock(amount);
        }
    }
    public int removeMoney(){
        int money = balance;
        balance = 0;
        return money;
    }

}
