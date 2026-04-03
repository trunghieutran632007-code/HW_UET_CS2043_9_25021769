package Bai6_04.src;

public class SorterAdapter implements Sorter {
    LegacySorter lgcSorter;

    public SorterAdapter(LegacySorter lgcSorter) {
        this.lgcSorter = lgcSorter;
    }

    @Override
    public int[] sort(int[] arr) {
        return lgcSorter.quickSort(arr);
    }

}
