package solutions.stackQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Deploy {

    static class Pair {
        int progress;
        int speed;
        Pair(int x, int y) {
            progress = x;
            speed = y;
        }
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        int N = progresses.length;
        Queue<Pair> que = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0;i<N;i++) {
            que.add(new Pair(progresses[i],speeds[i]));
        }

        while (!que.isEmpty()) {
            int queSize = que.size();
            boolean isDeploy = true;
            int cntDeploy = 0;
            while(queSize>0) {
                int progress = que.peek().progress;
                int speed = que.peek().speed;
                que.poll();
                progress = progress+speed>100 ? 100 : progress+speed;
                if (progress==100 && isDeploy) {
                    cntDeploy++;
                } else {
                    que.add(new Pair(progress,speed));
                    isDeploy = false;
                }
                queSize--;
            }
            if (cntDeploy>0) {
                list.add(cntDeploy);
            }
        }

        int listSize = list.size();
        answer = new int[listSize];

        for (int i = 0;i<listSize;i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    public static void main(String args[]) {
        int[] answer = solution(new int[]{95,90,99,99,80,99},new int[]{1,1,1,1,1,1});

        for (int num : answer) {
            System.out.printf("%d ",num);
        }
    }
}
