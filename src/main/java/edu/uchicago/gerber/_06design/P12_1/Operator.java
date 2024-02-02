package edu.uchicago.gerber._06design.P12_1;

public class Operator {
    private VendingMachine machine;

    public  Operator(VendingMachine machine){
        this.machine = machine;
    }

    public void restockProduct(String productName, int amount) {
        machine.restockProduct(productName, amount);
    }

    public int collectMoney() {
        return machine.removeMoney();
    }


}
