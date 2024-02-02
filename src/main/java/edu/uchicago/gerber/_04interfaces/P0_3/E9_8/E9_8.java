package edu.uchicago.gerber._04interfaces.P0_3.E9_8;

public class E9_8 {
    //Implement a subclass of BankAccount from How To 9.1 called BasicAccount whose withdraw method will not withdraw
    // more money than is currently in the account.
    public static void main(String[] args) {
        BasicAccount basicAccount = new BasicAccount();
        basicAccount.deposit(2000);
        basicAccount.deposit(1000);
        System.out.println("the current balance is: "+basicAccount.getBalance());
        basicAccount.withdraw(2500);
        System.out.println("the current balance is: "+basicAccount.getBalance());
        basicAccount.withdraw(1000);
        System.out.println("the current balance is: "+basicAccount.getBalance());
    }
}

