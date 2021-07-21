package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotCleaner {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        str = br.readLine();
        st = new StringTokenizer(str, " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        int map[][] = new int[60][60];
        boolean check[][] = new boolean[60][60];

        for (int i = 0; i < N; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        int dr[] = {-1, 0, 1, 0};
        int dc[] = {0, 1, 0, -1};

        while (true) {
            if (check[r][c]==false) {
                check[r][c] = true;
                answer++;
            }
            //System.out.println("Current : "+r+" "+c+" "+dir);
            boolean isEnableClear = false;
            for (int i = 0; i < 4; i++) {
                int ndir = dir == 0 ? 3 : dir - 1;
                int nr = r + dr[ndir];
                int nc = c + dc[ndir];
                if (map[nr][nc] == 0 && check[nr][nc] == false) {
                    dir = ndir;
                    r = nr;
                    c = nc;
                    isEnableClear = true;
                    break;
                } else {
                    dir = ndir;
                }
            }
            if (isEnableClear == false) {
                int ndir = (dir + 2) % 4;
                int nr = r + dr[ndir];
                int nc = c + dc[ndir];
                if (map[nr][nc]==1) {
                    break;
                } else {
                    r = nr;
                    c = nc;
                }
            }
        }
        System.out.println(answer);
    }
}
