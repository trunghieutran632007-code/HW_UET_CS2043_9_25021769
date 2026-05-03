import java.io.File;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Bai04: Matrix Strategy CI Test ===");
        System.out.println("OS name       : " + System.getProperty("os.name"));
        System.out.println("OS arch       : " + System.getProperty("os.arch"));
        System.out.println("Java version  : " + System.getProperty("java.version"));
        System.out.println("File.separator: \"" + File.separator + "\"");
        System.out.println();

        PathUtilsTest.runAll();
    }
}
