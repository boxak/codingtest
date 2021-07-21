package solutions.implementaition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FailRate {

    static class Pair implements Comparable<Pair> {
        int stage;
        double failRate;
        Pair(int x,double y) {
            stage = x;
            failRate = y;
        }

        public int compareTo(Pair pair) {
            if (this.failRate == pair.failRate) {
                return this.stage - pair.stage;
            }
            return pair.failRate - this.failRate > 0 ? 1 : -1;
        }
    }

    public static int[] solution(int N, int[] stages) {
        int[] answer = {};
        answer = new int[N];
        Arrays.sort(stages);
        int arrLen = stages.length;

        int[] arr = new int[N + 2];
        int stage = -1;

        for (int i = 0; i < arrLen; i++) {
            if (stage == stages[i]) {
                arr[stage]++;
            } else {
                stage = stages[i];
                arr[stage]++;
            }
        }

        int cnt = 0;
        ArrayList<Pair> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            System.out.println(i+" : "+arr[i]+" cnt : "+cnt);
            int notClear = arr[i];
            int challenge = arrLen - cnt;
            double failRate = 0;
            if (challenge == 0) failRate = 0;
            else failRate = ((double) notClear) / challenge;
            list.add(new Pair(i,failRate));
            cnt+=arr[i];
        }

        Collections.sort(list);
        for (int i =0;i<N;i++) {
            answer[i] = list.get(i).stage;
            System.out.println(list.get(i).stage+" "+list.get(i).failRate);
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] array = solution(5, new int[]{2,1,2,6,2,4,3,3});
        for (int num : array) {
            System.out.println(num);
        }
    }
}
