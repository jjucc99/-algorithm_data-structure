package sort;

import java.util.Arrays;
import java.util.stream.Stream;

public class StraightInsertionSort {
    static void insertionSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            int j;
            int tmp = a[i];
            for (j = i; j > 0 && a[j -1] > tmp ; j--) {
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{6, 4, 3, 7, 1, 9, 8};
        insertionSort(array, array.length);
        Arrays.stream(array).forEach(System.out::print);
    }
}
