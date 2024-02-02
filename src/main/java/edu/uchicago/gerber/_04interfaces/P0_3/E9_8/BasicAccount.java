package edu.uchicago.gerber._04interfaces.P0_3.E9_8;

import edu.uchicago.gerber._04interfaces.P0_3.E9_8.BankAccount;

public class BasicAccount extends BankAccount {
    public BasicAccount(){
        super();
    }
    public void withdraw(double amount) {
        double currentBalance = getBalance();
        if (amount < currentBalance) {
            super.withdraw(amount);
        } else {
            super.withdraw(currentBalance);
        }
    }
}
