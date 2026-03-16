package Bai3_01.src;

public class Person {
    String name;
    String dob;
/* 
    public Person(){
        System.out.println("1. Person is created");
        
    }
*/

//Sua thanh constructor co tham so
    public Person(String name) {
        this.name = name;
        System.out.println("1. Person is created");
    }
}
