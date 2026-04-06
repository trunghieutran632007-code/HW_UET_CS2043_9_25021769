package Bai6_10.src;

public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        if (logger1.hashCode() == logger2.hashCode()) {
            System.out.println("Logger instances equal: true");
        } else {
            System.out.println("Logger instances equal: false");
        }

        logger1.logInfo("Application started");
        logger1.logInfo("Processing data...");
        logger1.logError("Something went wrong");
    }

}
