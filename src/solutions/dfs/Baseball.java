package solutions.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baseball {

    static int N;
    static int[][] score;
    static int answer;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        score = new int[N+1][10];
        arr = new int[10];
        visited = new boolean[10];

        for (int i = 1;i<=N;i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");
            for (int j = 1;j<=9;j++) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MIN_VALUE;

        visited[1] = true;
        arr[4] = 1;
//        long time1 = System.currentTimeMillis();
        dfs(1);
//        long time2 = System.currentTimeMillis();
//        System.out.println(time2 - time1);
        System.out.println(answer);
    }

    static void dfs(int x) {
        if (x == 10) {
            simulation();
            return;
        }
        if (x == 4) dfs(x+1);
        else {
            for (int i = 2; i <= 9; i++) {
                if (!visited[i]) {
                    arr[x] = i;
                    visited[i] = true;
                    dfs(x + 1);
                    visited[i] = false;
                }
            }
        }
    }

    static void simulation() {

//        for (int i = 1;i<=9;i++) System.out.printf("%d ",arr[i]);
//        System.out.println();

        int ining = 1;
        int out = 0;
        int runner = 1;
        int point = 0;
        int[] home = new int[4];

        while(ining<=N) {
            out = 0;
            for (int i = 0;i<4;i++) home[i] = 0;
            while(out<3) {
                int player = arr[runner];
                int result = score[ining][player];
                if (result!=0) {
                    if (result == 4) {
                        point+=home[3]+home[2]+home[1]+1;
                        home[3] = 0;
                        home[2] = 0;
                        home[1] = 0;
                    }
                    if (result == 3) {
                        point+=home[3] + home[2] + home[1];
                        home[3] = 1;
                        home[2] = 0;
                        home[1] = 0;
                    }
                    if (result == 2) {
                        point+=home[3]+home[2];
                        home[3] = home[1];
                        home[2] = 1;
                        home[1] = 0;
                    }
                    if (result == 1) {
                        point+=home[3];
                        home[3] = home[2];
                        home[2] = home[1];
                        home[1] = 1;
                    }
                } else out++;
                runner = runner==9 ? 1 : runner+1;
            }
            ining++;
        }
        if (point>answer) answer = point;
    }
}
