package Bai6_04_b.src;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> sections = new ArrayList<>();
        sections.add("Muc 1: Gioi Thieu");
        sections.add("Muc 2: Phan than");
        sections.add("Muc 3: Phan ket");

        ReportTemplate originReport = new ReportTemplate("Bao cao mau", "Footer", sections);
        ReportTemplate copy1Report = originReport.clone();
        ReportTemplate copy2Report = originReport.clone();

        copy1Report.setTitle("Bao cao copy 1");
        copy2Report.setTitle("Bao cao copy 2");

        System.out.println("=== BAO CAO GOC ===");
        originReport.printReport();

        System.out.println("=== BAN SAO 1 ===");
        copy1Report.printReport();

        System.out.println("=== BAN SAO 2 ===");
        copy2Report.printReport();

    }

}
