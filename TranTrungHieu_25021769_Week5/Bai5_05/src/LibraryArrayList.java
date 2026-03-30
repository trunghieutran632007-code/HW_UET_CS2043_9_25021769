package Bai5_05.src;

import java.util.*;

public class LibraryArrayList {
    private List<Book> books;

    public LibraryArrayList() {
        books = new ArrayList<>();
    }

    // Them sach
    public void addBook(Book book) {
        books.add(book);
    }

    // Tim kiem sach theo id
    public Book searchById(String id) {
        for (Book b : books) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        System.out.println("Khong tim thay sach voi ID: " + id);
        return null;
    }

    // Xoa sach theo id
    public void deleteById(String id) {
        Book bookToRemove = searchById(id);
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Da xoa sach co ID: " + id);
        } else {
            System.out.println("Khong tim thay sach de xoa!");
        }
    }

    // In danh sach
    public void printBooks() {
        System.out.println("=== Danh sach (ArrayList) ===");
        for (Book b : books) {
            System.out.println(b);
        }
    }
}
