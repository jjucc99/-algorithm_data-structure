package linearSearch03_02;

public class SearchIndex {
    static int binSearchX(int[] a, int n, int key) {
        int pl = 0;
        int pr = n - 1;
        do {
            int pc = (pl + pr) / 2;
            if (a[pc] == key) {
                for (int i = pc; i >= pl; i--) {
                    if (key != a[i]) {
                        return i + 1;
                    }
                }
                return pc;
            } else if (a[pc] < key) {
                pl = pc + 1;
            }else {
                pr = pc - 1;
            }
        } while (pl <= pr);
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 7, 7, 7, 8, 8, 9, 9};
        SearchIndex searchIndex = new SearchIndex();
        int binSearchX = searchIndex.binSearchX(a, a.length, 7);
        System.out.println("binSearchX = " + binSearchX);
    }
}
