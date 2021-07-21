package solutions.implementaition;

import java.util.ArrayList;

public class Number124 {
    static public String solution(int n) {
        String answer = "";

        String s = toSamjinbeop(n);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0;i<s.length();i++) {
            list.add(s.charAt(i)-'0');
        }

        for (int i = list.size()-1;i>0;i--) {
            int cur = list.get(i);
            int before = list.get(i-1);
            if (cur==0) {
                list.set(i,4);
                list.set(i-1,before-1);
            } else if (cur==-1) {
                list.set(i,2);
                list.set(i-1,before-1);
            }
        }

        StringBuilder sb = new StringBuilder();

        if (list.get(0)==0) {
            for (int i = 1;i<list.size();i++) {
                sb.append(list.get(i));
            }
        } else {
            for (int i = 0;i<list.size();i++) {
                sb.append(list.get(i));
            }
        }

        answer = sb.toString();

        return answer;
    }

    static String toSamjinbeop(int n) {
        StringBuilder sb = new StringBuilder();
        while(n>0) {
            sb.append(n%3);
            n/=3;
        }
        StringBuilder sb2 = new StringBuilder();
        String temp = sb.toString();
        for (int i = temp.length()-1;i>=0;i--) {
            sb2.append(temp.charAt(i));
        }
        return sb2.toString();
    }

    public static void main(String args[]) {
        System.out.println(solution(2));
    }
}
