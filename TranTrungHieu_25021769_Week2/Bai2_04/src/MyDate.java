package Bai2_04.src;

public class MyDate {
    int date;
    int month;
    int year;
    public MyDate(int date, int month, int year){
        this.date = date;
        this.month = month;
        this.month = month;
    }

    public void setDate(int date){
        this.date = date;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void displayInfo(){
        System.out.println(this.date + "/" + this.month + "/" + this.year);
    }
    
    
}
