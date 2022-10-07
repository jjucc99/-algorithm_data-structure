package Queen;

public class EightQueen {
    // 각 행에 퀀을 배치했는 지 체크
    static boolean[] flag_a = new boolean[8];
    // 대각선 방향으로 퀸을 배치했는 지 체크
    static boolean[] flag_b = new boolean[15];
    // 대각선 방향으로 퀀을 배치했는 지 체크
    static boolean[] flag_c = new boolean[15];
    static int[] pos = new int[8];

    static void print() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pos[i] == j)
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void set(int i) {
        for (int j = 0; j < 8; j++) {
            if (!flag_a[j] // 가로 행에 아직 배치하지 않음
                    && !flag_b[i + j] //  / 대각선에 아직 배치하지 않음
                    && !flag_c[i - j + 7] // \ 대각선에 아직 배치 하지 않음
            ) {
                pos[i] = j;
                if (i == 7) {
                    print();
                } else {
                    flag_a[j] = flag_b[i + j] = flag_c[i - j + 7] = true;
                    set(i + 1);
                    flag_a[j] = flag_b[i + j] = flag_c[i - j + 7] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        set(0);
    }
}
