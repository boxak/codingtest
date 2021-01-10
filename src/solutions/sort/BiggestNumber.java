package solutions.sort;

import java.util.ArrayList;
import java.util.Collections;

public class BiggestNumber {

    static class Int implements Comparable<Int> {
        int num;

        Int(int x) {
            num = x;
        }

        @Override
        public int compareTo(Int o) {
            String str1 = ""+this.num+o.num;
            String str2 = ""+o.num+this.num;
            return Integer.parseInt(str2) - Integer.parseInt(str1);
        }
    }

    public static String solution(int[] numbers) {
        String answer = "";
        ArrayList<Int> list = new ArrayList<>();

        for (int num : numbers) {
            list.add(new Int(num));
        }

        Collections.sort(list);

        for (Int integer : list) {
            answer+=Integer.toString(integer.num);
        }

        if (Integer.parseInt(answer) == 0) answer = "0";

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{0,0,0}));
    }
}
