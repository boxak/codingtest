package solutions.stackQueue;

import java.util.ArrayList;

public class Deploy {
    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        int cntFinish = 0;
        int n = progresses.length;
        int[] arr = new int[n];
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0;i<n;i++) arr[i] = progresses[i];

        while(cntFinish < n) {
            for (int i = cntFinish;i<n;i++) {
                if (arr[i]<100) arr[i]+=speeds[i];
            }
            int cntDeploy = 0;
            while(arr[cntFinish]>=100) {
                cntFinish++;
                cntDeploy++;
                if (cntFinish>=n) break;
            }
            if (cntDeploy >= 1) list.add(cntDeploy);
        }

        answer = new int[list.size()];

        for (int i = 0;i<list.size();i++) answer[i] = list.get(i);

        return answer;
    }

    public static void main(String[] args) {

        int[] p = new int[]{95,90,99,99,80,99};
        int[] q = new int[]{1,1,1,1,1,1};

        int[] a = solution(p,q);

    }
}
