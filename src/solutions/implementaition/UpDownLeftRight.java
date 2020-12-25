package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class UpDownLeftRight {

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int r = 1, c = 1;

        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");

        while (st.hasMoreTokens()) {
            String dir = st.nextToken();
            int nr = r, nc = c;
            switch (dir) {
                case "L" :
                    nc--;
                    break;
                case "R" :
                    nc++;
                    break;
                case "U" :
                    nr--;
                    break;
                case "D" :
                    nr++;
                    break;
            }
            if (nr<1 || nr>N || nc<1 || nc>N) {
                continue;
            } else {
                r = nr;
                c = nc;
            }
        }
        System.out.printf("%d %d",r,c);
    }
}
