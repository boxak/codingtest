package solutions.stackQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class RotableQueue {

    static class MyQueue {
        private Deque<Integer> deque;

        MyQueue() {
            deque = new ArrayDeque<>();
        }

        public void pop() {
            if (!deque.isEmpty()) {
                deque.pollFirst();
            }
        }

        public void push(int num) {
            deque.addLast(num);
        }

        public void moveLeft() {
            if (!deque.isEmpty()) {
                int first = deque.pollFirst();
                deque.addLast(first);
            }
        }

        public void moveRight() {
            if (!deque.isEmpty()) {
                int last = deque.pollLast();
                deque.addFirst(last);
            }
        }

        public int size() {
            return deque.size();
        }

        public int getInx(int num) {
            int inx = -1;
            int cnt = 0;
            if (!deque.isEmpty()) {
                for (int n : deque) {
                    if (n == num) {
                        inx = cnt;
                        break;
                    }
                    cnt++;
                }
            }
            return inx;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = 0;

        MyQueue myQueue = new MyQueue();
        int[] numbers = new int[M];

        for (int i = 0;i<N;i++) {
            myQueue.push(i);
        }

        str = br.readLine();
        st = new StringTokenizer(str," ");

        for (int i = 0;i<M;i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0;i<M;i++) {
            int need = numbers[i]-1;
            int cnt = 0;
            int inx = myQueue.getInx(need);
            boolean isLeft = inx <= myQueue.size() - inx;

            if (isLeft) {
                cnt = inx;
                answer+=cnt;
                for (int j = 0;j<cnt;j++) {
                    myQueue.moveLeft();
                }
            } else {
                cnt = myQueue.size() - inx;
                answer+=cnt;
                for (int j = 0;j<cnt;j++) {
                    myQueue.moveRight();
                }
            }
            myQueue.pop();
        }
        System.out.println(answer);
    }
}
