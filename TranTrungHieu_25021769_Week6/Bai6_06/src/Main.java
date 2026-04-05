package Bai6_06.src;

public class Main {
    public static void main(String[] args) {
        // Khoi tao thu muc
        Folder root = new Folder("root");
        Folder docs = new Folder("docs");

        // Khoi tao file
        FileItem fileA = new FileItem("a.txt", 12);
        FileItem fileB = new FileItem("b.txt", 8);
        FileItem readme = new FileItem("readme.md", 4);

        // Khoi tao shortcut
        Shortcut shortcutA = new Shortcut("a-shortcut", fileA);

        // Xay dung cay
        docs.add(fileA);
        docs.add(fileB);
        docs.add(shortcutA);

        root.add(docs);
        root.add(readme);

        // In ra ket qua
        root.print("");

    }
}
