package Bai3_01.src;
public class Employee extends Person {
    double salary;
    
    public Employee() {
        super("name");
        System.out.println("2. Employee is created");
    }

}

/*
*Khi ta tao mot lop con ke thua lop cha, Java se ngam them dong "super()" o dong dau tien cua constructor cua lop con
*Do vay khi ta doi constructor cua class Person thanh constructor co tham so, dong super() trong constructor cua class
Employee khong nhan vao tham so, vay nen se bao loi
*Ta sua bang cach them super(co tham so) vao dong dau tien cua class Employee
*/
