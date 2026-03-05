package Bai2_03.src;

public class NumberWrapper {
    int value;
    public NumberWrapper(int value){
        this.value = value;
    }

    public NumberWrapper(){
        
    }

    public void swap(NumberWrapper a, NumberWrapper b){
        NumberWrapper temp = new NumberWrapper(1);
        temp = a;
        a = b;
        b = temp;
    }
    
    public void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        NumberWrapper no1 = new NumberWrapper(20);
        NumberWrapper no2 = new NumberWrapper();
        no2.setValue(10);
        System.out.println("Truoc khi swap");
        System.out.println("Gia tri cua doi tuong no1: " + no1.value);
        System.out.println("Gia tri cua doi tuong no2: " + no2.value);

        no1.swap(no1, no2);
        System.out.println("Sau khi swap");
        System.out.println("Gia tri cua doi tuong no1: " + no1.value);
        System.out.println("Gia tri cua doi tuong no2: " + no2.value);

        
    }
}
