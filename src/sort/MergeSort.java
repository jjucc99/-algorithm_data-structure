package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergeSort {
    static int[] buff;

    static void splitSort(int[] a, int left, int right) {
        if (left < right) {
            int i;
            int center = (left + right) / 2;
            int p = 0;
            int j = 0;
            int k = left;

            splitSort(a, left, center);
            splitSort(a, center + 1, right);

            for (i=left; i <= center ; i++)
                buff[p++] = a[i];
            while (i <= right && j < p) {
                a[k++] = (buff[j] <= a[i]) ? buff[j++] : a[i++];
}           while (j < p)
                a[k++] = buff[j++];
        }
    }

    static void mergeSort(int[] a, int n) {
        buff = new int[n];
        splitSort(a, 0, n -1);
        buff = null;
    }

    public static void main(String[] args) {
        int[] array = {6, 4, 3, 7, 1, 9, 8};
        mergeSort(array, array.length);
        Arrays.stream(array).forEach(System.out::println);
    }
}
