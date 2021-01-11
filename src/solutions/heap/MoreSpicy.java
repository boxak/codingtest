package solutions.heap;

import java.util.PriorityQueue;

public class MoreSpicy {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int food : scoville) {
            pq.add(food);
        }

        while(true) {
            int leastSpicy = pq.peek();
            if (leastSpicy>=K) break;
            else if (pq.size() == 1){
                answer = -1;
                break;
            }
            pq.poll();
            int secondLeastSpicy = pq.peek();
            pq.poll();
            int newSpicy = leastSpicy + 2*secondLeastSpicy;
            pq.add(newSpicy);
            answer++;
        }

        return answer;
    }
}
