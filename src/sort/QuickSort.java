package sort;

import java.util.Arrays;

public class QuickSort {

    static void swap(int[] a, int idx1, int idx2) {
        int tmp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = tmp;
    }

    static void quickSort(int[] a, int left, int right) {
        int pl = left;
        int pr = right;
        int x = a[(pl + pr) / 2];

        System.out.printf("a[%d] ~ a[%d]:{", left, right);
        for (int i = left; i < right; i++)
            System.out.printf("%d", a[i]);
        System.out.printf("%d}\n", a[right]);

        do {
            while (a[pl] < x) pl++;
            while (a[pr] > x) pr--;
            /*
             내림차순으로 정리
                while (a[pl] > x) pl++;
                while (a[pr] < x) pr--;
             */
            if (pl <= pr)
                swap(a, pl++, pr--);
        } while (pl <= pr);
        if (left < pr) quickSort(a, left, pr);
        if (right > pl) quickSort(a, pl, right);
    }

    public static void main(String[] args) {
        int[] array = {8, 1, 4, 2, 7, 6, 3, 5};
        quickSort(array, 0, array.length - 1);
        Arrays.stream(array).forEach(System.out::print);
    }
}
