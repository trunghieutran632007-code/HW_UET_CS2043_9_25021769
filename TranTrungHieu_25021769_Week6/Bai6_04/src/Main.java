package Bai6_04.src;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] testArray = {34, 7, 23, 32, 5, -9, 0, 62, 32, 1};
        LegacySorter legacySorter = new LegacySorter();

        Sorter sorterAdapter = new SorterAdapter(legacySorter);
        System.out.println("Mang truoc khi sap xep: \n" + Arrays.toString(testArray));

        System.out.println("Mang sau khi sap xep: \n" + Arrays.toString(sorterAdapter.sort(testArray)));
        
    }

}
