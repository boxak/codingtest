package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ReverseCardSet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] works = new int[10][2];

        for (int i = 0;i<10;i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");

            works[i][0] = Integer.parseInt(st.nextToken());
            works[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] cards = new int[21];

        for (int i = 1;i<=20;i++) cards[i] = i;

        for (int i = 0;i<10;i++) {
            int start = works[i][0];
            int end = works[i][1];

            int[] tempArr1 = new int[end-start+1];
            int[] tempArr2 = new int[end-start+1];

            for (int j = 0;j<end-start+1;j++) {
                tempArr1[j] = cards[start+j];
            }

            for (int j = 0;j<end-start+1;j++) {
                tempArr2[j] = tempArr1[end-start-j];
            }

            for (int j = 0;j<end-start+1;j++) {
                cards[start+j] = tempArr2[j];
            }

        }

        for (int i = 1;i<=20;i++) {
            System.out.printf("%d ",cards[i]);
        }

    }
}
