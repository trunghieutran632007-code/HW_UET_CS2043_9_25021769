package Bai3_04.src;

public class Main {
    public static void main(String[] args) {
        // Bước 1: Upcasting (An toàn)
        Animal a = new Dog(); // Dog kế thừa Animal (lấy từ Bài 2)
        // Bước 2: Downcasting (Rủi ro) - Hãy viết dòng này:
        //Cat c = (Cat) a;
        // Bước 3: Gọi hàm
        //c.makeSound();
        //Loi truoc khi sua: ClassCastException: class Bai3_04.src.Dog cannot be cast to class Bai3_04.src.Cat

        if (a instanceof Cat) {
            Cat c = (Cat) a;
            c.makeSound();
        } else {
            System.out.println("Day khong phai Meo!");
        }

    }

}
