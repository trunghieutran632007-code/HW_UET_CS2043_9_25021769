package Bai2_04.src;

public class Employee {
    String name;
    MyDate birthday;

    public Employee(String name, MyDate birthday){
        this.name = name;
        this.birthday = birthday;
    }

    public Employee(Employee otherEmp){
        this.name = otherEmp.name;
        this.birthday = new MyDate(otherEmp.birthday.date, otherEmp.birthday.month, otherEmp.birthday.year);
    }

    public void displayInfo(){
        System.out.println("Ten nhan vien: " + this.name);
        System.out.println("Ngay sinh: ");
        this.birthday.displayInfo();
    }

    //main
    public static void main(String[] args) {
        MyDate date1 = new MyDate(1, 1, 2000);
        Employee emp1 = new Employee("Tran Trung Hieu", date1);

        Employee emp2 = new Employee(emp1);

        System.out.println("Truoc khi thay doi: ");
        emp2.displayInfo();

        emp2.birthday.setDate(2);
        emp2.birthday.setMonth(2);
        emp2.birthday.setYear(2022);
        System.out.println("Sau khi thay doi: ");
        emp2.displayInfo();
    }
    
}
