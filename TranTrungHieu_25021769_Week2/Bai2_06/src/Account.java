package Bai2_06.src;
import java.util.Arrays;

public class Account {
    private final String accountId;
    private double balance;
    private Transaction[] history;
    private int count;

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.history = new Transaction[100];
        this.count = 0;
    }

    public void addTransaction(Transaction t){
        if (count < history.length){
            history[count] = t;
            count++;
        }
    }

    public Transaction[] getHistory(){
        return Arrays.copyOf(history, count);
    }

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

}
