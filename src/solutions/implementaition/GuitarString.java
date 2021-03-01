package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GuitarString {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int [][] arr = new int[M][2];

        for (int i = 0;i<M;i++) {
            str = br.readLine();
            st = new StringTokenizer(str," ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int minPackage = Integer.MAX_VALUE;
        int minIndivisual = Integer.MAX_VALUE;

        for (int i = 0;i<M;i++) {
            if (minPackage>arr[i][0]) minPackage = arr[i][0];
            if (minIndivisual>arr[i][1]) minIndivisual = arr[i][1];
        }

        int price1 = ((N/6)+1)*minPackage;
        int price2 = (N/6)*minPackage+(N%6)*minIndivisual;
        int price3 = N*minIndivisual;

        int answer = Math.min(price1,Math.min(price2,price3));

        System.out.println(answer);

    }
}
