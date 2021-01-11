package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;

public class Number124 {
    static public String solution(int n) {
        String answer = "";

        ArrayList<Integer> list = new ArrayList<>();

        while(n>0) {
            list.add(n%3);
            n/=3;
        }
        Collections.reverse(list);
        int listSize = list.size();

        for (int i = listSize-1;i>0;i--) {
            int num = list.get(i);
            int beforeNum = list.get(i-1);
            if (num == 0) {
                list.set(i, 4);
                list.set(i-1,beforeNum-1);
            } else if (num == -1) {
                list.set(i, 2);
                list.set(i-1, beforeNum-1);
            }
        }

        if (list.get(0)==0) {
            for (int i = 1;i<listSize;i++) {
                answer+=list.get(i);
            }
        } else {
            for (int i = 0;i<listSize;i++) {
                answer+=list.get(i);
            }
        }

        return answer;
    }

    public static void main(String args[]) {
        System.out.println(solution(19));
    }
}
