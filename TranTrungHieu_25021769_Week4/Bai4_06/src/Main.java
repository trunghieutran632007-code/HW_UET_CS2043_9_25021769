package Bai4_06.src;


public class Main {
    public static void main(String[] args) {
        Integer[] nums = {5, 1, 3, 2};
        ArrayUtils.sort(nums);
        String ans1 = "";
        for (int i = 0; i < nums.length; i++) {
            ans1 += nums[i];
            ans1 += " ";
        }
        System.out.println(ans1);
        

        String[] langs = {"Java", "C++", "Python"};
        ArrayUtils.sort(langs);
        String ans2 = "";
        for (int i = 0; i < langs.length; i++) {
            ans2 += langs[i];
            ans2 += " ";
        }
        System.out.println(ans2);

        Student[] students = {
            new Student("Dang", 3.9),
            new Student("Hoang",   3.8),
            new Student("mHieu", 4.0),
            new Student("tHieu", 3.4)
        };
        ArrayUtils.sort(students);
        String ans3 = "";
        for (int i = 0; i < students.length; i++) {
            ans3 += students[i].toString();
            ans3 += " ";
        }
        System.out.println(ans3);


    }

}
