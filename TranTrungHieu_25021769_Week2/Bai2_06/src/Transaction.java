package Bai2_06.src;

public class Transaction {
    private final String transactionId ;
    private final double amount;
    private final String timestamp;

    public Transaction(String transactionId, double amount, String timestamp){
        this.transactionId = transactionId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    
}
