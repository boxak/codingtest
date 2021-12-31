package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MoneyHunter {
    public static void main(String[] args) throws IOException {
        int[] moneys1 = new int[]{5_000_000, 3_000_000, 2_000_000, 500_000, 300_000, 100_000};
        int[] moneys2 = new int[]{5120000, 2560000, 1280000, 640000, 320000};

        int[] rewards1 = new int[100];
        int[] rewards2 = new int[64];

        int s1 = 0;
        int s2 = 0;
        int e1 = 1;
        int e2 = 1;

        int idx1 = 0;
        int idx2 = 0;

        int diff1 = 1;
        int diff2 = 1;

        for (int i = 0;i<6;i++) {
            for (int j = s1;j<e1;j++) {
                rewards1[j] = moneys1[idx1];
            }
            s1 = e1;
            e1 = e1 + diff1+1;
            diff1++;
            idx1++;
        }

        for (int i = 0;i<5;i++) {
            for (int j = s2;j<e2;j++) {
                rewards2[j] = moneys2[idx2];
            }
            s2 = e2;
            e2 = e2 + diff2*2;
            diff2*=2;
            idx2++;
        }

        ArrayList<Integer> answerList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0;i<N;i++) {
            String str = br.readLine();
            int first = Integer.parseInt(str.split(" ")[0]);
            int second = Integer.parseInt(str.split(" ")[1]);

            int sum = (first==0 ? 0 : rewards1[first - 1]) + (second==0 ? 0 : rewards2[second - 1]);

            answerList.add(sum);
        }

        for (int answer : answerList) {
            System.out.println(answer);
        }
    }
}
