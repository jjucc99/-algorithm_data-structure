package sort;

import java.util.Arrays;

public class ShellSort {
    static void shellSort(int[] a, int n) {
        for (int h = n / 2; h > 0; h /= 2) {
            for (int i = h; i < n; i++) {
                int j;
                int tmp = a[i];
                for (j = i - h; j >= 0 && a[j] > tmp; j -= h) {
                    a[j + h] = a[j];
                }
                a[j + h] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {8, 1, 4, 2, 7, 6, 3, 5};
        shellSort(array, array.length);
        Arrays.stream(array).forEach(System.out::print);
    }
}
