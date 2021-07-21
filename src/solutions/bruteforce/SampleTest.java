package solutions.bruteforce;

import java.util.ArrayList;

public class SampleTest {
    public int[] solution(int[] answers) {
        int[] answer = {};

        int N = answers.length;
        int cnt1 = 0, cnt2 = 0, cnt3 = 0;

        int[] answerSet1 = {1, 2, 3, 4, 5};
        int[] answerSet2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] answerSet3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        for (int i = 0; i < N; i++) {
            int answer1 = answerSet1[i % 5];
            int answer2 = answerSet2[i % 8];
            int answer3 = answerSet3[i % 10];
            if (answer1 == answers[i]) cnt1++;
            if (answer2 == answers[i]) cnt2++;
            if (answer3 == answers[i]) cnt3++;
        }

        int[] scores = {cnt1, cnt2, cnt3};

        int max = -1;

        for (int i = 0; i < 3; i++) {
            if (max < scores[i]) {
                max = scores[i];
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if (max == scores[i]) {
                list.add(i);
            }
        }

        int listSize = list.size();
        ;
        answer = new int[listSize];

        for (int i = 0; i < listSize; i++) {
            answer[i] = list.get(i) + 1;
        }

        return answer;
    }
}
