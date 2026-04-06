package Bai6_08.src;

public class JsonFormatter implements ReportFormatter {
    @Override
    public String format(Report report) {
        return "{\n" +
                "  \"title\": \"" + report.getTitle() + "\",\n" +
                "  \"content\": \"" + report.getContent() + "\"\n" +
                "}";
    }

}
