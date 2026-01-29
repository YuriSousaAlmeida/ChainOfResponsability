package org.example;

public abstract class Account {
    protected Account account;
    protected double balance;

    public void nextAccount(Account account) {
        this.account = account;
    }

    public double balance() {
        return balance;
    }

    public void pay(double amount) {
        if(canPay(amount)) {
            System.out.println("You have paid $"+amount + " in " + getClass().getSimpleName());
            this.balance -= amount;
            System.out.println("You have: " + this.balance);
        }else if(account != null) {
            System.out.println("Not enough money here: " + getClass().getSimpleName());
            account.pay(amount);
        }else{
            throw new RuntimeException("You can´t pay with any account");
        }
    }

    public boolean canPay(double amount) {
        return this.balance >= amount;
    }

}
