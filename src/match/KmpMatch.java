package match;

public class KmpMatch {
    static int kmpMatch(String tex, String pat) {
        // txt 커서
        int pt = 1;
        // pat 커서
        int pp = 0;
        // 건너뛰기 표
        int[] skip = new int[pat.length() + 1];

        // 건너 뛰기 표
        skip[pt] = 0;
        while (pt != pat.length()) {
            // 뛰어쓰기 검사를 한다.
            if (pat.charAt(pt) == pat.charAt(pp))
                skip[++pt] = ++pp;
            // 뛰어쓰기 검사를 했는 데 틀렸고 pp가 0이다.
            else if (pp == 0)
                skip[++pt] = pp;
            // 뛰어쓰기 검사를 했는 데 틀렸고 pp가 0도 아니다.
            else
                pp = skip[pp];
        }

        // 검색
        pt = pp = 0;
        while (pt != tex.length() && pp != pat.length()) {
            if (tex.charAt(pt) == pat.charAt(pp)) {
                pt++;
                pp++;
            } else if (pp == 0)
                pt++;
            else
                pp = skip[pp];
            if (pp == pat.length())
                return pt - pp;
        }
        return -1;
    }

    public static void main(String[] args) {
        String tex = "ZABCABXACCADEF";
        String pat = "ABCABD";

        int match = kmpMatch(tex, pat);
    }
}
