package Bai4_10.src;

public class Book extends MediaItem {
    private String author;
    private int pages;

    public Book(String id, String name, String author, int pages) {
        super(id, name);
        this.author = author;
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }
    public int getPages() {
        return pages;
    }

    @Override
    public String displayInfo() {

        return getName() + " - " + author + " - " + pages + " trang";
    }
}
