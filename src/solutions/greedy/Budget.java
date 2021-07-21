package solutions.greedy;

import java.util.Arrays;

public class Budget {
    public int solution(int[] d,int budget) {
        int answer = 0;

        Arrays.sort(d);
        int arrLen = d.length;

        for (int i = 0;i<arrLen;i++) {
            if (budget>=d[i]) {
                budget-=d[i];
                answer++;
            }
        }

        return answer;
    }
}
