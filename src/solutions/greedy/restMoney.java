package solutions.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class restMoney {
    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N;
        N = Integer.parseInt(br.readLine());

        int restMoney = 1000 - N;

        int coins[] = {500, 100, 50, 10, 5, 1};
        int answer = 0;

        for (int i = 0; i < 6; i++) {
            answer+=restMoney/coins[i];
            restMoney = restMoney%coins[i];
        }
        System.out.println(answer);
    }
}
