package edu.uchicago.gerber._06design.P12_1;

public class Main {

    public static void main(String[] args){
        VendingMachine machine = new VendingMachine();

        machine.addProduct(new Product("Lemonade", 150, 10));
        machine.addProduct(new Product("Soda", 100, 20));
        machine.addProduct(new Product("Orange Juice", 200, 5));

        System.out.println(machine.selectProduct("Lemonade", 200)); // Should dispense water
        System.out.println(machine.selectProduct("Orange Juice", 150)); // Should say insufficient money

        Operator operator = new Operator(machine);
        operator.restockProduct("Orange Juice", 10); // Restocking juice
        int moneyCollected = operator.collectMoney(); // Collecting money from the machine
        System.out.println("Money collected from the machine: " + moneyCollected);
    }

}

