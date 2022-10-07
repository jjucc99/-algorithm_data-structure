package sort;

import java.util.Arrays;
import java.util.Stack;

public class QuickSort2 {

    static void swap(int[] a, int idx1, int idx2) {
        int tmp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = tmp;
    }

    static void quickSort(int[] a, int left, int right) {

        Stack<Integer> lstack = new Stack<>();
        Stack<Integer> rstack = new Stack<>();

        lstack.push(left);
        rstack.push(right);

        while (!lstack.isEmpty() && !rstack.isEmpty()) {
            int pl = left = lstack.pop();
            int pr = right = rstack.pop();

            int x = a[(left + right) / 2];

            do {
                while (a[pl] < x) pl++;
                while (a[pr] > x) pr--;
                if (pl <= pr)
                    swap(a, pl++, pr--);
            } while (pl <= pr);

            if (left < pr) {
                lstack.push(left);
                rstack.push(pr);
            }
            if (right > pl) {
                lstack.push(pl);
                rstack.push(right);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {8, 1, 4, 2, 7, 6, 3, 5};
        quickSort(array, 0, array.length - 1);
        Arrays.stream(array).forEach(System.out::print);
    }
}
