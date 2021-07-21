package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;

public class TwoSelectPlus {
    public int[] solution(int[] numbers) {
        int[] answer = {};

        ArrayList<Integer> list = new ArrayList<>();
        int length = numbers.length;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) continue;
                int sum = numbers[i] + numbers[j];
                if (!list.contains(sum)) {
                    list.add(sum);
                }
            }
        }

        Collections.sort(list);

        int listLen = list.size();
        answer = new int[listLen];

        for (int i = 0; i < listLen; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
