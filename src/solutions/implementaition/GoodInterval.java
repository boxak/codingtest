package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GoodInterval {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        int S[] = new int[L];
        for (int i = 0;i<L;i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }
        int n = Integer.parseInt(br.readLine());
        int answer = 0;

        for (int i=1;i<=1000;i++) {
            int s = i;
            for (int j=s+1;j<=1000;j++) {
                int e = j;
                if (n<s || n>e) continue;
                boolean flag = true;
                for (int k=0;k<L;k++) {
                    int num = S[k];
                    if (s<=num && num<=e) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}
