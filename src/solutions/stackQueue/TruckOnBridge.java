package solutions.stackQueue;

import java.util.LinkedList;
import java.util.Queue;

public class TruckOnBridge {

    class Pair {
        int weight;
        int time;

        Pair(int x, int y) {
            weight = x;
            time = y;
        }
    }

    public int solution(int bridge_length,
                        int weight, int[] truck_weights) {
        int answer = 0;

        Queue<Integer> waitQue = new LinkedList<>();
        Queue<Pair> bridgeQue = new LinkedList<>();
        int weightOnBridge = 0;
        int cntFinish = 0;

        for (int truck : truck_weights) {
            waitQue.add(truck);
        }

        while (cntFinish<truck_weights.length) {
            int bqSize = bridgeQue.size();
            for (int i = 0; i < bqSize; i++) {
                int truckWeight = bridgeQue.peek().weight;
                int time = bridgeQue.peek().time;
                bridgeQue.poll();
                time--;
                if (time == 0) {
                    weightOnBridge -= truckWeight;
                    cntFinish++;
                } else {
                    bridgeQue.add(new Pair(truckWeight, time));
                }
            }
            if (!waitQue.isEmpty()) {
                int hWeight = waitQue.peek();
                if (weightOnBridge + hWeight <= weight) {
                    waitQue.poll();
                    bridgeQue.add(new Pair(hWeight, bridge_length));
                    weightOnBridge += hWeight;
                }
            }
            answer++;
        }

        return answer;
    }
}
