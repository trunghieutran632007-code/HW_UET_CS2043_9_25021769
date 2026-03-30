package Bai5_05.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryArrayList libraryArrayList = new LibraryArrayList();
        LibraryHashMap libraryHashMap = new LibraryHashMap();
        LibraryTreeMap libraryTreeMap = new LibraryTreeMap();

        Book book1 = new Book("B01", "Dac Nhan Tam", "Dale Carnegie", 1936);
        Book book2 = new Book("B02", "Nha Gia Kim", "Paulo Coelho", 1988);
        Book book3 = new Book("B03", "Toi Ac va Hinh Phat", "Fyodor Dostoevsky", 1866);
        Book book4 = new Book("B04", "So Do", "Vu Trong Phung", 1936);
        Book book5 = new Book("B05", "Luoc Su Loai Nguoi", "Yuval Noah Harari", 2011);

        //Them sach vao ArrayList
        libraryArrayList.addBook(book1);
        libraryArrayList.addBook(book2);
        libraryArrayList.addBook(book3);
        libraryArrayList.addBook(book4);
        libraryArrayList.addBook(book5);

        //Them sach vao HashMap
        libraryHashMap.addBook(book1);
        libraryHashMap.addBook(book2);
        libraryHashMap.addBook(book3);
        libraryHashMap.addBook(book4);
        libraryHashMap.addBook(book5);

        //Them danh sach vao Tree Map
        libraryTreeMap.addBook(book1);
        libraryTreeMap.addBook(book2);
        libraryTreeMap.addBook(book3);
        libraryTreeMap.addBook(book4);
        libraryTreeMap.addBook(book5);

        //In ket qua
        libraryArrayList.printBooks();
        System.out.println();
        libraryHashMap.printBooks();
        System.out.println();
        libraryTreeMap.printBooks();
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        //Tim kiem theo id
        System.out.println("Nhap id sach can tim kiem: ");
        String id1 = scanner.nextLine();
        System.out.println(libraryArrayList.searchById(id1));
        System.out.println(libraryHashMap.searchById(id1));
        System.out.println(libraryTreeMap.searchById(id1));

        //Xoa theo id
        System.out.println("Nhap id sach can xoa: ");
        String id2 = scanner.nextLine();
        libraryArrayList.deleteById(id2);
        libraryHashMap.deleteById(id2);
        libraryTreeMap.deleteById(id2);

        //In ket qua
        libraryArrayList.printBooks();
        System.out.println();
        libraryHashMap.printBooks();
        System.out.println();
        libraryTreeMap.printBooks();
        System.out.println();

        scanner.close();
    }

}
