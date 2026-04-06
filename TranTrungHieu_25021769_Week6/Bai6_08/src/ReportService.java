package Bai6_08.src;

public class ReportService {
    private ReportFormatter formatter;

    public ReportService(ReportFormatter formatter) {
        this.formatter = formatter;
    }

    public String export(Report data) {
        return formatter.format(data);
    }

    public void setFormatter(ReportFormatter formatter) {
        this.formatter = formatter;
    }

}
