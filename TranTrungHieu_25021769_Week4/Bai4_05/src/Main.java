package Bai4_05.src;

public class Main {
    public static void main(String[] args) {
        Pair<String, Integer> p1 = new Pair<>("Tuoi", 20);
        Pair<String, String> p2 = new Pair<>("Ma SV", "SV001");
        Pair<Integer, Double> p3 = new Pair<>(105, 21.5);
        //Pair<Integer, Double> p4 = new Pair<>("105", 21.5);
        //Bao loi: Cannot infer type arguments for Pair<>

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
    }

}
