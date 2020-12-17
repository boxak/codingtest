package solutions.greedy;

import java.util.Scanner;

public class Until1 {
    public static void main(String args[]) {
        int N,K;
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        K = scan.nextInt();

        int answer = 0;
        while(N>1) {
            if (N%K==0) N/=K;
            else N--;
            answer++;
        }
        System.out.println(answer);
    }
}
