package sort;

import java.util.Arrays;


class HeapSort {
    //--- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
    static void swap(int[] a, int idx1, int idx2) {
        int t = a[idx1];
        a[idx1] = a[idx2];
        a[idx2] = t;
    }

    //--- a[left]～a[right]를 힙으로 만들기 ---//
    static void downHeap(int[] a, int left, int right) {
        int temp = a[left];        // 뿌리
        int child;                 // 큰 쪽의 자식
        int parent;                // 부모

        for (parent = left; parent < (right + 1) / 2; parent = child) {
            int cl = parent * 2 + 1;        // 왼쪽의 자식
            int cr = cl + 1;                // 오른쪽의 자식
            child = (cr <= right && a[cr] > a[cl]) ? cr : cl;    // 큰 쪽
            if (temp >= a[child])
                break;
            a[parent] = a[child];
        }
        a[parent] = temp;
    }

    //--- 힙 정렬 ---//
    static void heapSort(int[] a, int n) {
        for (int i = (n - 1) / 2; i >= 0; i--)    // a[i]~a[n-1]를 힙으로 만듦
            downHeap(a, i, n - 1);

        for (int i = n - 1; i > 0; i--) {
            swap(a, 0, i);                       // 가장 큰 요소와 아직 정렬되지 않은 부분의 마지막 요소를 교환
            downHeap(a, 0, i - 1);               // a[0] ~ a[i-1]을 힙으로 만듦
        }
    }

    public static void main(String[] args) {
        int[] array = {6, 4, 3, 7, 1, 9, 8};
        heapSort(array, array.length);
        Arrays.stream(array).forEach(System.out::print);
    }
}