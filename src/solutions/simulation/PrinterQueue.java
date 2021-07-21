package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PrinterQueue {

    static class Pair {
        int inx;
        int importance;

        Pair(int x, int y) {
            inx = x;
            importance = y;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCnt = Integer.parseInt(br.readLine());

        int[] answers = new int[testCnt];

        for (int test = 0; test < testCnt; test++) {
            int N, M;
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            str = br.readLine();
            st = new StringTokenizer(str, " ");

            Queue<Pair> que = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                int importance = Integer.parseInt(st.nextToken());
                que.add(new Pair(i, importance));
            }

            int answer = 1;

            while (true) {
                int inx = que.peek().inx;
                int importance = que.peek().importance;
                boolean flag = false;
                que.poll();
                Iterator<Pair> iter = que.iterator();

                while(iter.hasNext()) {
                    Pair pair = iter.next();
                    if (pair.importance > importance) {
                        flag = true;
                    }
                }

                if (!flag) {
                    if (inx==M) {
                        break;
                    } else {
                        answer++;
                    }
                } else {
                    que.add(new Pair(inx, importance));
                }
            }
            answers[test] = answer;
        }
        for (int answer : answers) {
            System.out.println(answer);
        }
    }
}
