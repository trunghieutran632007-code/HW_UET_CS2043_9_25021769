package Bai3_02.src;

public class Animal {
    public void makeSound() {
        System.out.println("Animal sound");
    }

}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof woof");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meows meows");
    }
}

class Duck extends Animal {
    
}
