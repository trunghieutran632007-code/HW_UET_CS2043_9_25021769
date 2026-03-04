class BankAccount {
    private final String accountNumber;
    private double balance;
    private final String ownerName;

    public BankAccount(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        if (balance < 0) {
            this.balance = 0;
            System.out.println("Loi: So du nho hon 0!");
        } else {
            this.balance = balance;
        }
    }

    public BankAccount(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0; 
    } 

    public void deposit(double amount) {
        if (amount < 0) {
            System.out.println("So tien nap vao phai lon hon 0!"); 
        } else {
            this.balance = this.balance + amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount < 0) {
            System.out.println("So tien rut ra phai lon hon 0!");
            return false;
        }
        if (amount > this.balance) {
            System.out.println("Khong du so du!");
            return false;
        } else {
            this.balance = this.balance - amount;
            return true;
        }
    }

    public double getBalance() {
        return this.balance;
    }
    public void displayInfo() {
        System.out.println("--- Thong tin tai khoan ---");
        System.out.println("So tai khoan: " + this.accountNumber);
        System.out.println("Chu tai khoan: " + this.ownerName);
        System.out.println("So du hien tai: " + this.balance);
        System.out.println("---------------------------");
    }

    public static void main(String[] args) {
        //Dinh danh
        BankAccount myAccount = new BankAccount("12345", "Nguyen Tien Hoang", 500000);
        myAccount.displayInfo();
        //Nap vao tk 1 trieu
        myAccount.deposit(1000000);
        myAccount.displayInfo();
        //Rut khoi tk 2 trieu --> loi
        myAccount.withdraw(2000000);
        myAccount.displayInfo();
        //Nap tien am
        myAccount.deposit(-1000000);
        myAccount.displayInfo();
        



        

    }
}