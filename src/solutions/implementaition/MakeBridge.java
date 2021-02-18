package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class MakeBridge {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int answers[] = new int[T];
        for (int test = 0;test<T;test++) {
            int n,m;
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            long cnt = 0;

            BigInteger big = new BigInteger("1");
            BigInteger small = new BigInteger("1");

            int inx = m-n>n ? m-n : n;

            for (int i = m;i>inx;i--) {
                big = big.multiply(new BigInteger(i+""));
            }

            for (int i = 1;i<=m-inx;i++) {
                small = small.multiply(new BigInteger(i+""));
            }

            big = big.divide(small);

            answers[test] = Integer.parseInt(big.toString());
        }

        for (int answer : answers) {
            System.out.println(answer);
        }

    }

}
