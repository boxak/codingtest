package solutions.implementaition;

import java.util.Stack;

public class BracketTransform {
    public static String solution(String p) {
        String answer = "";
        answer = process(p);
        return answer;
    }

    public static String process(String p) {
        if (check(p)) return p;
        else return transform(p);
    }

    public static String transform(String w) {
        if (w.isEmpty()) return w;
        String u = "";
        String v = "";
        int inx = 0;
        int cnt1 = 0;
        int cnt2 = 0;
        int wLen = w.length();
        for (int i = 0; i < wLen; i++) {
            if (w.charAt(i) == '(') cnt1++;
            if (w.charAt(i) == ')') cnt2++;
            if (cnt1 == cnt2) {
                inx = i;
                break;
            }
        }
        if (inx < wLen - 1) {
            u = w.substring(0, inx + 1);
            v = w.substring(inx + 1);
        } else {
            u = w;
            v = "";
        }
        //System.out.println("u : "+u+", v : "+v);
        if (check(u)) {
            v = transform(v);
            w = u + v;
            return w;
        } else {
            v = transform(v);
            String tmp = "";
            int uLen = u.length();
            for (int i = 1; i < uLen - 1; i++) {
                if (u.charAt(i)=='(') {
                    tmp+=")";
                } else {
                    tmp+="(";
                }
            }
            w = "(" + v + ")" + tmp;
            return w;
        }
    }

    public static boolean check(String w) {
        Stack<Character> stack = new Stack<>();
        int wLen = w.length();
        for (int i = 0; i < wLen; i++) {
            char c = w.charAt(i);
            if (c == '(') {
                stack.add(c);
            } else {
                if (stack.isEmpty()) return false;
                else stack.pop();
            }
        }
        return true;
    }

    public static void main(String args[]) {
        String p = "()))((()";
        System.out.println(solution(p));
    }
}
