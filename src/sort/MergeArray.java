package sort;

import java.util.Arrays;

public class MergeArray {
    // 정렬을 마친 배열 a, b를 병합하여 c에 저장
    static void merge(int[] a, int na, int[] b, int nb, int[] c) {
        int pa = 0;
        int pb = 0;
        int pc = 0;

        while (pa < na && pb < nb)
            c[pc++] = (a[pa] <= b[pb]) ? a[pa++] : b[pb++];

        while (pa < na)
            c[pc++] = a[pa++];
        while (pb < nb)
            c[pc++] = b[pb++];
    }

    public static void main(String[] args) {
        int[] a = {2, 4, 6, 8, 11, 13};
        int[] b = {1, 2, 3, 4, 9, 16, 21};
        int[] c = new int[(a.length + b.length)];
        merge(a, a.length, b, b.length, c);
        Arrays.stream(c).forEach(System.out::println);
    }
}
