package Bai5_05.src;

import java.util.*;

public class LibraryTreeMap {
    private Map<String, Book> bookMap;

    public LibraryTreeMap() {
        bookMap = new TreeMap<>();
    }

    // Them sach (keys la id)
    public void addBook(Book book) {
        bookMap.put(book.getId(), book);
    }

    // Tim kiem sach theo id
    public Book searchById(String id) {
        if (bookMap.containsKey(id)) {
            return bookMap.get(id);
        }
        System.out.println("Khong tim thay sach voi ID: " + id);
        return null;
    }

    // Xoa sach theo id
    public void deleteById(String id) {
        if (bookMap.containsKey(id)) {
            bookMap.remove(id);
            System.out.println("Da xoa sach co ID: " + id);
        } else {
            System.out.println("Khong tim thay sach de xoa!");
        }
    }

    // In danh sach
    public void printBooks() {
        System.out.println("=== Danh sach (TreeMap)===");
        for (Book b : bookMap.values()) {
            System.out.println(b.toString());
        }
    }
}
