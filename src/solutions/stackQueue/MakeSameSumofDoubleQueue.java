package solutions.stackQueue;

import java.util.LinkedList;
import java.util.Queue;

public class MakeSameSumofDoubleQueue {
    public static int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        Queue<Integer> que1 = new LinkedList<>();
        Queue<Integer> que2 = new LinkedList<>();

        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0;i<queue1.length;i++) {
            que1.add(queue1[i]);
            que2.add(queue2[i]);
            sum1+=(long)queue1[i];
            sum2+=(long)queue2[i];
        }

        while(sum1!=sum2) {
            if (sum1 > sum2) {
                int moveNum = que1.poll();
                que2.add(moveNum);
                sum1-=moveNum;
                sum2+=moveNum;
            } else if (sum1 < sum2) {
                int moveNum = que2.poll();
                que1.add(moveNum);
                sum1+=moveNum;
                sum2-=moveNum;
            }

            if (que1.isEmpty() || que2.isEmpty()) return -1;

            if (answer > 600_000) {
                return -1;
            }

            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] queue1 = {1,1};
        int[] queue2 = {1,5};

        System.out.println(solution(queue1, queue2));
    }
}
