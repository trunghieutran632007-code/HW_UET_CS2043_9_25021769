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

    public String getTransactionID() {return this.transactionId;}
    public double getAmount() {return this.amount;}
    public String getTimestamp() {return timestamp;}

    public void displayInfo(){
        System.out.println(this.transactionId+ " " + this.amount + " thoi gian: " + this.timestamp);
    }

    
}
