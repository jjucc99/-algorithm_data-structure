package match;

public class BfMatch {

    static int bfMatch(String txt, String pat) {
        int pt = 0;
        int pp = 0;

        while (pt != txt.length() && pp != pat.length()) {
            if (txt.charAt(pt) == pat.charAt(pp)) {
                pt++;
                pp++;
            } else {
                pt = pt - pp + 1;
                pp = 0;
            }
        }
        if (pp == pat.length())
            return pt - pp;
        return -1;
    }

    public static void main(String[] args) {
        String tex = "ABC이지스DEF";
        String pat = "이지스";

        int match = bfMatch(tex, pat);
        for (int i = 0; i < pat.length(); i++) {
            String search = String.valueOf(tex.charAt(match));
            System.out.println(search);
            match++;
        }
    }
}
