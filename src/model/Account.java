package model;

public class Account {
    private int accNumber;
    private String name;
    private double balance;

    public Account(int accNumber, String name, double balance) {
        this.accNumber = accNumber;
        this.name = name;
        this.balance = balance;
    }

    public int getAccNumber() { return accNumber; }
    public String getName() { return name; }
    public double getBalance() { return balance; }

    @Override
    public String toString() {
        return "Account Number: " + accNumber + ", Name: " + name + ", Balance: " + balance;
    }
}

