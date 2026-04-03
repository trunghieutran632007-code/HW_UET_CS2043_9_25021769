package Bai6_05.src;

public class ExportApp {
    public static Export getExportInstance(String type) {
        if (type.equalsIgnoreCase("PDF")) {
            return new PdfExport();
        } else if (type.equalsIgnoreCase("Excel")) {
            return new ExcelExport();
        } else {
            return null;
        }
    }

}
