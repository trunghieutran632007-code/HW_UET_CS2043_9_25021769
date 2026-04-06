package Bai6_08.src;

public class XmlFormatter implements ReportFormatter {
    @Override
    public String format(Report report) {
        return "<report>\n" +
                "  <title>" + report.getTitle() + "</title>\n" +
                "  <content>" + report.getContent() + "</content>\n" +
                "</report>";
    }

}
