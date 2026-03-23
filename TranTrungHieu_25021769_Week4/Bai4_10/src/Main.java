package Bai4_10.src;

public class Main {
    public static void main(String[] args) {

        // Khu vuc Sach
        LibrarySection<Book> bookSection = new LibrarySection<>("Khu vuc Sach");
        bookSection.add(new Book("B01", "Dac Nhan Tam", "Dale Carnegie", 320));
        bookSection.add(new Book("B02", "Nha Gia Kim", "Paulo Coelho", 228));
        bookSection.add(new Book("B03", "Sapiens", "Yuval Noah Harari", 512));

        System.out.println();
        bookSection.display();

        System.out.println();
        bookSection.remove("B02");
        bookSection.remove("B99"); // id khong ton tai

        System.out.println();
        bookSection.display();

        // Khu vuc DVD
        System.out.println();
        LibrarySection<DVD> dvdSection = new LibrarySection<>("Khu vuc DVD");
        dvdSection.add(new DVD("D01", "Inception", "Christopher Nolan", 148));
        dvdSection.add(new DVD("D02", "Interstellar", "Christopher Nolan", 169));
        dvdSection.add(new DVD("D03", "The Matrix", "Wachowski", 136));

        System.out.println();
        dvdSection.display();

        // Cac tinh huong bi loi:
        //bookSection.add(new DVD("D01", "Inception", "Nolan", 148));
        //--> Loi: The method add(Book) in the type LibrarySection<Book> is not applicable for the arguments (DVD)
    }
}
