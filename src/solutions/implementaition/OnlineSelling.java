package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class OnlineSelling {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] prices = new int[M];

        int maxRevenue = -1;
        int bestPrice = Integer.MAX_VALUE;

        for (int i = 0;i<M;i++) {
            prices[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(prices);

        for (int i = 0;i<M;i++) {
            int price = prices[i];
            int cnt = N>(M-i) ? (M-i) : N;
            int sum = price*cnt;

            if (maxRevenue<sum) {
                maxRevenue = sum;
                bestPrice = price;
            }
        }

        System.out.println(bestPrice+" "+maxRevenue);

    }
}
