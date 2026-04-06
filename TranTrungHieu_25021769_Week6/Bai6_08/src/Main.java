package Bai6_08.src;

public class Main {
    public static void main(String[] args) {
        // 1. Create a report
        Report myReport = new Report("Bao cao doanh thu", "Tong doanh thu: 1000 USD");

        // 2. Select formatter and inject into ReportService
        
        // Export using JSON
        System.out.println("=== Exporting as JSON ===");
        ReportFormatter jsonFormatter = new JsonFormatter();
        ReportService service = new ReportService(jsonFormatter);
        System.out.println(service.export(myReport));

        System.out.println();

        // Export using XML 
        System.out.println("=== Exporting as XML ===");
        ReportFormatter xmlFormatter = new XmlFormatter();
        service.setFormatter(xmlFormatter); 
        System.out.println(service.export(myReport));

        System.out.println();

        //Export using txt
        System.out.println("=== Exporting as Text ===");
        ReportFormatter txtFormatter = new TextFormatter();
        service.setFormatter(txtFormatter); 
        System.out.println(service.export(myReport));

    }
}
