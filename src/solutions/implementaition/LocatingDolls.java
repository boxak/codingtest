package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocatingDolls {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int N = Integer.parseInt(str.split(" ")[0]);
        int K = Integer.parseInt(str.split(" ")[1]);

        int[] cnts = new int[N];

        str = br.readLine();
        String[] split = str.split(" ");

        for (int i=0;i<N;i++) {
            cnts[i] = Integer.parseInt(split[i]);
        }

        int sum = 0;
        int s = 0;
        int e = K-1;

        for (int i=0;i<K;i++) {
            sum+=cnts[i];
        }

        double avg = (double)sum/K;
        double distribution = getDistribution(cnts, s, e, avg, K);
        double deviation = Math.sqrt(distribution);

        double minValue = deviation;

        while(e<N-1) {
            e++;
            sum+=cnts[e]-cnts[s];
            s++;
            avg = (double)sum/K;
            distribution = getDistribution(cnts, s, e, avg, K);
            deviation = Math.sqrt(distribution);

            if (deviation<minValue) minValue = deviation;
        }

        System.out.println(String.format("%.11f",minValue));
    }

    static double getDistribution(int[] cnts, int s,int e,double avg, int K) {
        double sum = 0;
        for (int i = s;i<=e;i++) {
            sum+=(cnts[i]-avg)*(cnts[i]-avg);
        }

        return sum/K;
    }
}
