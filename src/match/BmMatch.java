package match;

public class BmMatch {
    static int bmMatch(String txt, String pat) {
        int pt;
        int pp;
        int txtlen = txt.length();
        int patlen = pat.length();
        int[] skip = new int[Character.MAX_VALUE + 1];

        // 건너뛰기 표 만들기
        for (pt = 0; pt <= Character.MAX_VALUE; pt++)
            skip[pt] = patlen;
        for (pt = 0; pt< patlen - 1; pt++)
            skip[pat.charAt(pt)] = patlen - pt -1;

        //검색
        while (pt < txtlen) {
            pp = patlen - 1;

            while (txt.charAt(pt) == pat.charAt(pp)) {
                if (pp == 0)
                    return pt;
                pp--;
                pt--;
            }
            pt += (skip[txt.charAt(pt)]> patlen - pp) ? skip[txt.charAt(pt)] : patlen - pp;

        }
        return -1;
    }
    public static void main(String[] args) {
        String tex = "ZABCABXACCADEF";
        String pat = "ABCABD";

        int match = bmMatch(tex, pat);
    }
}
