package solutions.sort;

import java.util.ArrayList;
import java.util.Collections;

public class KthNumber {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = {};

        int N = commands.length;
        ArrayList<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1] - 1;
            int K = commands[i][2] - 1;

            ArrayList<Integer> list = new ArrayList<>();

            for (int j = start; j <= end; j++) {
                list.add(array[j]);
                System.out.println(array[j]);
            }
            System.out.println();
            Collections.sort(list);
            answerList.add(list.get(K));
        }

        int listSize = answerList.size();
        answer = new int[listSize];
        for (int i = 0; i < listSize; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}
