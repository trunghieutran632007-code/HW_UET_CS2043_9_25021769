package Bai6_04_b.src;

import java.util.ArrayList;
import java.util.List;

public class ReportTemplate implements Cloneable {
    private String title;
    private String footer;
    private List<String> sections;

    public ReportTemplate(String title, String footer, List<String> sections) {
        this.title = title;
        this.footer = footer;
        this.sections = sections;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public void setSections(List<String> sections) {
        this.sections = sections;
    }

    @Override
    public ReportTemplate clone() {
        try {
            ReportTemplate cloned = (ReportTemplate) super.clone();

            if (this.sections != null) {
                // deep copy
                cloned.sections = new ArrayList<>(this.sections);
            }

            return cloned;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printReport() {
        System.out.println("Title: " + this.title);
        System.out.println("Sections: " + this.sections);
        System.out.println("Footer: " + this.footer);
        System.out.println("-------------------------------------------------");
    }

}
