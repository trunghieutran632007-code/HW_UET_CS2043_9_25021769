package Bai2_08.src;

public class Person {
    private String name;
    private Person me;

    // 2. Hàm khởi tạo (Constructor)
    public Person(String name) {
        this.name = name;
    }

    // 3. Phương thức (Methods)
    public void setMe(Person other) {
        this.me = other;
    }

    public Person getMe() {
        return this.me;
    }

    public String getName() {
        return this.name;
    }
    
}
