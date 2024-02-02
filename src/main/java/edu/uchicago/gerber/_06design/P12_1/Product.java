package edu.uchicago.gerber._06design.P12_1;

public class Product {
    private String name;
    private int price;
    private int quantity;

    public Product(String name, int price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return quantity > 0;
    }

    public void dispense() {
        if (quantity > 0) {
            quantity--;
        }
    }

    public void restock(int amount) {
        quantity += amount;
    }

}
