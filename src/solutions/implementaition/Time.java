package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Time {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int second = 0;
        int minute = 0;
        int hour = 0;

        int answer = 0;

        while (!(hour == N && minute == 59 && second == 59)) {
            String str = hour + " " + minute + " " + second;
            if (str.indexOf("3") != -1) {
                answer++;
            }
            second++;
            if (second == 60) {
                minute++;
                second = 0;
            }
            if (minute == 60) {
                hour++;
                minute = 0;
            }
        }
        System.out.println(answer);
    }
}
