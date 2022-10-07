package sort;

public class BubbleSort3 {
    static void swap(int[] a, int idx1, int idx2) {
        int tmp = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = tmp;
    }

    // 버블 정렬
    static void bubbleSort3(int[] a, int n) {
        int k = 0;
        while (k < n - 1) {
            int last = n - 1;
            for (int i = n-1; i > k; i--) {
                if (a[i - 1] > a[i]) {
                    swap(a, i - 1, i);
                    last = i;
                }
            }
            k = last;
        }
    }

    public static void main(String[] args) {
        bubbleSort3(new int[]{6, 4, 3, 7, 1, 9, 8}, 7);
    }
}
