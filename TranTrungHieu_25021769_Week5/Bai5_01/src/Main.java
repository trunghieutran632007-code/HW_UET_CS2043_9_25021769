package Bai5_01.src;

public class Main {

    public static void useString() {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < 100000; i++) {
            s += "Hello";
        }
        long end = System.currentTimeMillis();
        System.out.println("useString time: " + (end - start) + " ms");
    }

    public static void useStringBuffer() {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100000; i++) {
            sb.append("Hello");
        }
        long end = System.currentTimeMillis();
        System.out.println("useStringBuffer time: " + (end - start) + " ms");
    }

    public static void contentAnalysis() {
        String text = "Java la ngon ngu lap trinh manh me. Ban co thich Java khong? "
                    + "Java duoc dung rat rong rai! Nhieu cong ty su dung Java. "
                    + "Ban nen hoc Java ngay hom nay? Day la mot y kien hay!";

        // Dem so luong cau (dua vao dau . ? !)
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '.' || c == '?' || c == '!') {
                count++;
            }
        }
        System.out.println("So luong cau: " + count);

        // Tim va thay the "Java" thanh "Python"
        StringBuffer sb = new StringBuffer(text);
        String target = "Java";
        String replacement = "Python";
        int index = sb.indexOf(target);
        while (index != -1) {
            sb.replace(index, index + target.length(), replacement);
            index = sb.indexOf(target, index + replacement.length());
        }
        System.out.println("Sau khi thay the: " + sb.toString());
    }

    public static void main(String[] args) {
        useString();
        useStringBuffer();
        contentAnalysis();
    }
}
