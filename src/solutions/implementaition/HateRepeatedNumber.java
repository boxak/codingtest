package solutions.implementaition;

import java.util.ArrayList;

public class HateRepeatedNumber {
    public int[] solution(int[] arr) {
        int[] answer = {};

        ArrayList<Integer> list = new ArrayList<>();
        int arrLen = arr.length;

        list.add(arr[0]);

        for (int i = 1;i<arrLen;i++) {
            if (arr[i-1]!=arr[i]) {
                list.add(arr[i]);
            }
        }

        int listSize = list.size();
        answer = new int[listSize];

        for (int i =0;i<listSize;i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
