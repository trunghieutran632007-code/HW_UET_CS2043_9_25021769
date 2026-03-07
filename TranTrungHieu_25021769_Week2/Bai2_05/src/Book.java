package Bai2_05.src;

public class Book {
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price){
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {return true;}
        if (obj == null || getClass() != obj.getClass()) {return false;}
        Book other = (Book) obj;

        return this.title.equals(other.title)
            && this.author.equals(other.author)
            && Double.compare(this.price, other.price) == 0;
        

    }

    public static void main(String[] args) {
        Book b1 = new Book("Vat ly", "NXBGD", 10.5);
        Book b2 = new Book("Vat ly", "NXBGD", 10.5);
        
        System.out.println("So sanh ==: " + (b1 == b2));
        System.out.println("So sanh equals(): " + (b1.equals(b2)));

        // == so sanh dia chi cua b1 va b2, nen se tra ve false do b1 va b2 khac nhau ve dia chi bo nho
        //equals() cung so sanh dia chi bo nho (neu ta khong override)
    }

    
}


