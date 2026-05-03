package vn.uet.lapnangcao;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bai 9_08: Dong goi san pham thuc thi voi Maven ===");
        System.out.println();
        System.out.println("Ung dung da duoc dong goi thanh file JAR boi Maven.");
        System.out.println("Java version : " + System.getProperty("java.version"));
        System.out.println("Main-Class   : " + Main.class.getName());
        System.out.println("JAR location : " +
            Main.class.getProtectionDomain().getCodeSource().getLocation());
        System.out.println();
        System.out.println("Cach chay truc tiep: java -jar target/bai9-08.jar");
    }
}
