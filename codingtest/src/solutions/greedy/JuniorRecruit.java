package solutions.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class JuniorRecruit {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCnt = Integer.parseInt(br.readLine());
        int[] answers = new int[testCnt];
        for (int test = 0; test < testCnt; test++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<Junior> list = new ArrayList<>();
            int answer = 1;

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                StringTokenizer st = new StringTokenizer(str, " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.add(new Junior(a, b));
            }

            Collections.sort(list);

            int topRankingInSecond = list.get(0).second;

            for (int i = 1; i < N; i++) {
                int secondScore = list.get(i).second;
                if (secondScore<topRankingInSecond) {
                    answer++;
                    topRankingInSecond = secondScore;
                }
            }

            answers[test] = answer;
        }

        for (int answer : answers) {
            System.out.println(answer);
        }
    }

    static class Junior implements Comparable<Junior> {
        int first;
        int second;

        Junior(int x, int y) {
            first = x;
            second = y;
        }

        public int compareTo(Junior junior) {
            return this.first - junior.first;
        }
    }
}
