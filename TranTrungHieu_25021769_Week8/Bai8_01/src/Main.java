package Bai8_01.src;
public class Main {
 
    public static void main(String[] args) {
        System.out.println("=== Bai 1: The Smell Hunter - Refactored Demo ===");
 
        demoSectionA();
        demoSectionB();
        demoSectionC();
        demoSectionD();
    }
 
    // ---------- Section A ----------
    private static void demoSectionA() {
        System.out.println("\n--- Section A ---");
        FeeCalculator calc = new FeeCalculator();
 
        double feeRegular = calc.calculateFee(10, 50.0, false);
        double feeMember  = calc.calculateFee(10, 50.0, true);
 
        System.out.println("10h * 50.0/h, regular : " + feeRegular);
        System.out.println("10h * 50.0/h, member  : " + feeMember);
    }
 
    // ---------- Section B ----------
    private static void demoSectionB() {
        System.out.println("\n--- Section B ---");
        UserRepository     repo     = new UserRepository();
        EmailService       emails   = new EmailService();
        UserProfileRenderer renderer = new UserProfileRenderer();
        UserCsvExporter    exporter = new UserCsvExporter();
 
        User user = repo.findById(1);
        emails.sendWelcomeEmail(user);
        emails.sendPasswordResetEmail(user);
        renderer.render(user);
        System.out.println("[CSV] " + exporter.export(user));
    }
 
    // ---------- Section C ----------
    private static void demoSectionC() {
        System.out.println("\n--- Section C ---");
        Shape[] shapes = {
                new Rectangle(4, 5),
                new Triangle(3, 6),
                new Circle(2)
        };
        for (Shape s : shapes) {
            System.out.println(s.getClass().getSimpleName()
                    + " area = " + s.getArea());
        }
    }
 
    // ---------- Section D ----------
    private static void demoSectionD() {
        System.out.println("\n--- Section D ---");
        Author author = new Author(
                "Palmer",
                "palmer@example.com",
                "0900000000",
                "Hanoi"
        );
        Report report = new Report("Q4 Summary", "Report content here", author);
 
        System.out.println("Title : " + report.getTitle());
        System.out.println("Author: " + report.getAuthor().getName()
                + " <" + report.getAuthor().getEmail() + ">");
        System.out.println("Phone : " + report.getAuthor().getPhone());
        System.out.println("Addr  : " + report.getAuthor().getAddress());
    }
}