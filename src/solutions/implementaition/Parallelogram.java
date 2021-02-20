package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Parallelogram {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        int x[] = new int[4];
        int y[] = new int[4];

        double max = 0.0;
        double min = 100000000000.0;

        for (int i = 0; i < 3; i++) {
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        int[][] comb = new int[3][3];

        comb[0][0] = 0;
        comb[1][0] = 1;
        comb[2][0] = 2;
        comb[0][1] = 0;
        comb[1][1] = 2;
        comb[2][1] = 1;
        comb[0][2] = 1;
        comb[1][2] = 2;
        comb[2][2] = 0;

        double d1 = Math.abs(((double)(y[0]-y[1]))/((double)(x[0]-x[1])));
        double d2 = Math.abs(((double)(y[2]-y[1]))/((double)(x[2]-x[1])));

        if (d1 == d2) {
            System.out.println("-1.0");
            return;
        }

        for (int i = 0;i<3;i++) {
            int inx1 = comb[0][i];
            int inx2 = comb[1][i];
            int inx3 = comb[2][i];

            double l1 = Math.sqrt((x[inx1] - x[inx2]) * (x[inx1] - x[inx2]) + (y[inx1] - y[inx2]) * (y[inx1] - y[inx2]));
            double l2 = Math.sqrt((x[inx2] - x[inx3]) * (x[inx2] - x[inx3]) + (y[inx2] - y[inx3]) * (y[inx2] - y[inx3]));

            double length1 = 2.0 * l1 + 2.0 * l2;

            if (length1 < min) min = length1;
            if (length1 > max) max = length1;

            double l3 = Math.sqrt((x[inx1]-x[inx2])*(x[inx1]-x[inx2])+(y[inx1]-y[inx2])*(y[inx1]-y[inx2]));
            double l4 = Math.sqrt((x[inx1]-x[inx3])*(x[inx1]-x[inx3])+(y[inx1]-y[inx3])*(y[inx1]-y[inx3]));

            double length2 = 2.0 * l3 + 2.0 * l4;

            if (length2 < min) min = length2;
            if (length2 > max) max = length2;
        }

        System.out.println(max-min);

    }
}
