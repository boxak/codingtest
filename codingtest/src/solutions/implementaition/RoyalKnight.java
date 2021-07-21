package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RoyalKnight {

    static final int dr[] = {-2, -2, -1, 1, 2, 2, 1, -1};
    static final int dc[] = {-1, 1, 2, 2, 1, -1, -2, -2};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int r = str.charAt(1) - '0';
        int c = str.charAt(0) - 'a' + 1;

        int answer = 0;

        for (int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 1 && nr <= 8 && nc >= 1 && nc <= 8) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
