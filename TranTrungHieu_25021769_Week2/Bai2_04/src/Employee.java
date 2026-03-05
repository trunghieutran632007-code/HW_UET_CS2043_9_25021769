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

    //main
    public static void main(String[] args) {
        MyDate date1 = new MyDate(1, 1, 2000);
        Employee emp1 = new Employee("Tran Trung Hieu", date1);

        MyDate date2 = new MyDate(6, 3, 2007);
        Employee emp2 = new Employee("Tran Trung Hieu", date2);
    }
    
}
