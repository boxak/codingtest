package solutions.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class coinZero {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N,K;
        String str1 = br.readLine();
        StringTokenizer st = new StringTokenizer(str1," ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int coins[] = new int[N];

        for (int i = 0;i<N;i++) {
            String str2 = br.readLine();
            coins[i] = Integer.parseInt(str2);
        }

        int answer = Integer.MAX_VALUE;

        for (int i = N-1;i>=0;i--) {
            int tempNum = K;
            int totalCoinCnt = 0;
            for (int j = i;j>=0;j--) {
                if (coins[j]<=tempNum) {
                    totalCoinCnt+=tempNum/coins[j];
                    tempNum=tempNum%coins[j];
                }
            }
            if (answer>totalCoinCnt) answer = totalCoinCnt;
        }
        System.out.println(answer);
    }
}
