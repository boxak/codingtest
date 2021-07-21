package solutions.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Rope {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int ropes[] = new int[N];

        for (int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ropes);
        int answer = 0;

        for (int i = N-1;i>=0;i--) {
            int weight = (N-i)*ropes[i];
            if (weight>answer) answer = weight;
        }

        System.out.println(answer);
    }
}
