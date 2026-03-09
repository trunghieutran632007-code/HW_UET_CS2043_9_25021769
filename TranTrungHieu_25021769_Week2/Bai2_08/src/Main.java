package Bai2_08.src;

public class Main {
    public static void main(String[] args) {
        Person p = new Person("TTH");

        p.setMe(p);
        System.out.println("Ten: " + p.getMe().getName());
        p = null;
    }
    
}
