package sort;

import java.util.Arrays;

public class QuickSort3 {
    static void swap(int[] a, int idx1, int idx2) {
        int tmp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = tmp;
    }

    // x[a], x[b], x[c] 를 정렬(가운데 값의 인덱스를 반환)
    static int sort3elem(int[] x, int a, int b, int c) {
        if (x[b] < x[a]) swap(x, b, a);
        if (x[c] < x[b]) swap(x, c, b);
        if (x[b] < x[a]) swap(x, b, a);
        return b;
    }

    static void quickSort(int[] a, int left, int right) {
        int pl = left;
        int pr = right;
        // 처음, 끝, 가운데 요소를 정렬
        int mid = sort3elem(a, pl, (pl + pr) / 2, pr);
        // 피벗
        int x = a[mid];
        // 가운데 요소와 끝에서 두 번쨰 요소를 교환
        swap(a, mid, right - 1);
        // 왼쪽 커서를 오른쪽에서 1만큼 진행
        pl++;
        // 오른쪽 커서를 왼쪽으로 2만큼 진행
        pr -= 2;
        do{
            while (a[pl] < x) pl++;
            while (a[pr] > x) pr--;
            if (pl <= pr)
                swap(a, pr++, pl--);
        } while (pl <= pr);
        if (left < pr) quickSort(a, left, pr);
        if (pl< right) quickSort(a, pl, right);
    }
    public static void main(String[] args) {
        int[] array = {8, 1, 4, 2, 7, 6, 3, 5};
        quickSort(array, 0, array.length - 1);
        Arrays.stream(array).forEach(System.out::print);
    }
}
