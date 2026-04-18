public class Report {
 
    private final String title;
    private final String content;
    private final Author author;
 
    public Report(String title, String content, Author author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
 
    public String getTitle()   { return title; }
    public String getContent() { return content; }
    public Author getAuthor()  { return author; }
}