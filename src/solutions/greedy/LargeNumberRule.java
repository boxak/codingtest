package solutions.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class LargeNumberRule {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int N = 0, M = 0, K = 0;
        N = scan.nextInt();
        M = scan.nextInt();
        K = scan.nextInt();

        int numArray[] = new int[N];
        int check = 0;
        for (int i = 0; i < N; i++) {
            numArray[i] = scan.nextInt();
        }

        Arrays.sort(numArray);

        int answer = 0;

        int count = (int)(M/(K+1))*K;
        count+=M%(K+1);

        answer+=count*numArray[N-1];
        answer+=(M-count)*numArray[N-2];

        System.out.println(answer);
    }
}
