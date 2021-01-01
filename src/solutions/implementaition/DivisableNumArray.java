package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;

public class DivisableNumArray {
    public int[] solution(int[] arr,int divisor) {
        int[] answer = {};

        ArrayList<Integer> list = new ArrayList<>();
        for (int num : arr) {
            if (num%divisor==0) {
                list.add(num);
            }
        }

        if (list.isEmpty()) answer = new int[]{-1};

        else {
            Collections.sort(list);
            int listSize = list.size();
            answer = new int[listSize];
            for (int i =0;i<listSize;i++) {
                answer[i] = list.get(i);
            }
        }

        return answer;
    }
}
