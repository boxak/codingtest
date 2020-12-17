package solutions.greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ATM {
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = 0;
        N = Integer.parseInt(br.readLine());

        int processTimes[] = new int[N];
        int watingTimes[] = new int[N];

        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");

        for (int i=0;i<N;i++) {
            processTimes[i] = Integer.parseInt(st.nextToken());
            watingTimes[i] = 0;
        }

        Arrays.sort(processTimes);
        for (int i = 0;i<N;i++) {
            int sum = 0;
            for (int j = 0;j<=i;j++) {
                sum+=processTimes[j];
            }
            watingTimes[i] = sum;
        }

        int answer = 0;
        for (int i = 0;i<N;i++) {
            answer+=watingTimes[i];
        }
        System.out.println(answer);
    }
}
