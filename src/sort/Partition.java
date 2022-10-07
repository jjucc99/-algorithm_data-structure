package sort;

import java.util.Arrays;

public class Partition {
    static void swap(int[] a, int idx1, int idx2) {
        int tmp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = tmp;
    }

    static void partition(int[] a, int n) {
        int pl = 0;
        int pr = n - 1;
        int x = a[n / 2];

        do {
            while (a[pl] < x) pl++;
            while (a[pr] > x) pr--;
            if (pl <= pr)
                swap(a, pl++, pr--);
        } while (pl <= pr);
    }

    public static void main(String[] args) {
        int[] array = {1, 8, 7, 4, 5, 2, 6, 3, 9};
        partition(array, array.length);
        Arrays.stream(array).forEach(System.out::print);
    }
}
