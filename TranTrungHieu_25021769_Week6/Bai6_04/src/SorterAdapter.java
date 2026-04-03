package Bai6_04.src;

public class SorterAdapter implements Sorter {
    LegacySorter lgcSorter;

    public SorterAdapter(LegacySorter lgcSorter) {
        this.lgcSorter = lgcSorter;
    }

    @Override
    public int[] sort(int[] arr) {

        // Kiem tra mang rong hoac chi co 1 phan tu
        if (arr == null || arr.length <= 1) {
            return arr;
        }

        // Goi ham de quy bat dau tu phan tu dau den phan tu cuoi
        quickSort(arr, 0, arr.length - 1);

        return arr;
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi la chi so cua chot (pivot), arr[pi] da nam dung vi tri
            int pi = partition(arr, low, high);

            // Sap xep de quy cac phan tu truoc va sau chot
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        // Chon phan tu cuoi cung lam chot
        int pivot = arr[high];

        // Chi so cua phan tu nho hon
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            // Neu phan tu hien tai nho hon hoac bang chot
            if (arr[j] <= pivot) {
                i++;

                // Hoan doi arr[i] va arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Hoan doi arr[i+1] va arr[high] de dua chot ve dung vi tri
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Tra ve vi tri cua chot
        return i + 1;
    }

}
