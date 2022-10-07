package sort;

import java.util.Arrays;

public class CountingSort {
    // 도수 정렬 (0 이상 max 이하의 값을 입력)
    static void countingSort(int[] a, int n, int max) {
        int[] f = new int[max + 1];
        int[] b = new int[n];
        // 1단계 도수 분표표를 만들기 => f
        for (int i = 0; i < n; i++) f[a[i]]++;
        // 2 단계 누적 도수 분포표을 만들기 => f
        for (int i = 1; i <= max; i++) f[i] += f[i - 1];
        // 3 단계 목표 배열 만들기
        for (int i = n - 1; i >= 0; i--) b[--f[a[i]]] = a[i];
        // 4 단계 배열 복사하기
        for (int i = 0; i < n; i++) a[i] = b[i];
    }

    public static void main(String[] args) {
        int[] arrays = {22, 5, 11, 32, 120, 68, 70};

        int max = arrays[0];
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] > max) max = arrays[i];
        }
        countingSort(arrays, arrays.length, max);
        Arrays.stream(arrays).forEach(System.out::println);
    }
}
