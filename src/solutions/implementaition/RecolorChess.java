package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RecolorChess {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int answer = Integer.MAX_VALUE;

        int map[][] = new int[n][m];

        for (int i = 0;i<n;i++) {
            str = br.readLine();
            for (int j = 0;j<m;j++) {
                map[i][j] = str.charAt(j)=='W' ? 0 : 1;
            }
        }

        for (int i = 0;i<=n-8;i++) {
            for (int j = 0;j<=m-8;j++) {
                int color1 = 0;
                int change1 = 0;
                for (int f = i;f<i+8;f++) {
                    for (int k = j;k<j+8;k++) {
                        if (map[f][k]!=color1) {
                            change1++;
                        }
                        if (k<j+7) {
                            color1 = 1 - color1;
                        }
                    }
                }
                int color2 = 1;
                int change2 = 0;
                for (int f = i;f<i+8;f++) {
                    for (int k = j;k<j+8;k++) {
                        if (map[f][k]!=color2) {
                            change2++;
                        }
                        if (k<j+7) {
                            color2 = 1 - color2;
                        }
                    }
                }
                answer = Math.min(answer,Math.min(change1,change2));
            }
        }
        System.out.println(answer);
    }
}
