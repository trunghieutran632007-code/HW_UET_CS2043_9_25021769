package Bai7_03.src;

public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void deposit(int amount) {
        synchronized (this) {
            balance += amount;
        }
    }

    public void withdraw(int amount) {
        synchronized (this) {
            balance -= amount;
        }
    }

    public int getBalance() {
        return balance;
    }

}
