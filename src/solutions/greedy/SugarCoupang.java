package solutions.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SugarCoupang {
    public static void main(String args[]) throws Exception {
        int N;

        int answer = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int cnt3 = 0;
        int cnt5 = 0;

        if (N % 5 == 0) {
            cnt3 = 0;
            cnt5 = N / 5;
        } else if (N % 5 == 1) {
            cnt3 = 2;
            cnt5 = N / 5 - 1;
        } else if (N % 5 == 2) {
            cnt3 = 4;
            cnt5 = N / 5 - 2;
        } else if (N % 5 == 3) {
            cnt3 = 1;
            cnt5 = N / 5;
        } else if (N % 5 == 4) {
            cnt3 = 3;
            cnt5 = N / 5 - 1;
        }

        answer = cnt3 + cnt5;
        switch (N) {
            case 1:
                answer = -1;
                break;
            case 2:
                answer = -1;
                break;
            case 4:
                answer = -1;
                break;
            case 7:
                answer = -1;
                break;
        }
        System.out.println(answer);
    }
}
