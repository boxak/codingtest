package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Game2048 {

    static int N;
    static int[][] map;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int[] arr = new int[5];
    static int[][] cmap;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        cmap = new int[N+1][N+1];
        answer = Integer.MIN_VALUE;

        for (int i = 1;i<=N;i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");
            for (int j = 1;j<=N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(answer);

    }

    static void dfs(int x) {
        if (x==5) {
            simulation();
            return;
        }
        for (int i = 0;i<4;i++) {
            arr[x] = i;
            dfs(x+1);
            arr[x] = 0;
        }
    }

    static void simulation() {

        for (int i = 1;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                cmap[i][j] = map[i][j];
            }
        }

        for (int i = 0;i<5;i++) {
            moveBlocks(arr[i]);
        }
    }

    static void moveBlocks(int d) {
        switch (d) {
            case 0 :
                moveUp();
                break;
            case 1 :
                moveRight();
                break;
            case 2 :
                moveDown();
                break;
            case 3 :
                moveLeft();
                break;
        }

        int maxBlock = Integer.MIN_VALUE;

        for (int i = 1;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                if (maxBlock < cmap[i][j]) maxBlock = cmap[i][j];
            }
        }

        if (maxBlock > answer) answer = maxBlock;
    }

    static void moveUp() {
        boolean[][] repeatCheck = new boolean[N+1][N+1];

        for (int i = 2;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                int r = i;
                int c = j;
                int num = cmap[i][j];
                cmap[r][c] = 0;
                boolean isMeet = false;

                for (int k = i-1;k>=0;k--) {
                    if (cmap[k][c]>0) {
                        isMeet = true;
                        if (cmap[k][c]!=num) {
                            cmap[k+1][c] = num;
                        } else {
                            if (repeatCheck[k][c]) {
                                cmap[k+1][c] = num;
                            } else {
                                cmap[k][c]+=num;
                                repeatCheck[k][c] = true;
                            }
                        }
                        break;
                    }
                }

                if (!isMeet) cmap[1][c] = num;

            }
        }
    }

    static void moveDown() {
        boolean[][] repeatCheck = new boolean[N + 1][N + 1];

        for (int i = N - 1; i >= 1; i--) {
            for (int j = 1; j <= N; j++) {
                int r = i;
                int c = j;
                int num = cmap[i][j];
                cmap[r][c] = 0;
                boolean isMeet = false;

                for (int k = i + 1; k <= N; k++) {
                    if (cmap[k][c] > 0) {
                        isMeet = true;
                        if (cmap[k][c] != num) {
                            cmap[k - 1][c] = num;
                        } else {
                            if (repeatCheck[k][c]) {
                                cmap[k - 1][c] = num;
                            } else {
                                cmap[k][c] += num;
                                repeatCheck[k][c] = true;
                            }
                        }
                        break;
                    }
                }

                if (!isMeet) cmap[N][c] = num;

            }
        }
    }

    static void moveRight() {
        boolean[][] repeatCheck = new boolean[N + 1][N + 1];

        for (int j = N - 1; j >= 1; j--) {
            for (int i = 1; i <= N; i++) {
                int r = i;
                int c = j;
                int num = cmap[i][j];
                cmap[r][c] = 0;
                boolean isMeet = false;

                for (int k = j + 1; k <= N; k++) {
                    if (cmap[r][k] > 0) {
                        isMeet = true;
                        if (cmap[r][k] != num) {
                            cmap[r][k-1] = num;
                        } else {
                            if (repeatCheck[r][k]) {
                                cmap[r][k-1] = num;
                            } else {
                                cmap[r][k] += num;
                                repeatCheck[r][k] = true;
                            }
                        }
                        break;
                    }
                }

                if (!isMeet) cmap[r][N] = num;

            }
        }
    }

    static void moveLeft() {
        boolean[][] repeatCheck = new boolean[N + 1][N + 1];

        for (int j = 2; j <= N; j++) {
            for (int i = 1; i <= N; i++) {
                int r = i;
                int c = j;
                int num = cmap[i][j];
                cmap[r][c] = 0;
                boolean isMeet = false;

                for (int k = j - 1; k >= 0; k--) {
                    if (cmap[r][k] > 0) {
                        isMeet = true;
                        if (cmap[r][k] != num) {
                            cmap[r][k+1] = num;
                        } else {
                            if (repeatCheck[r][k]) {
                                cmap[r][k+1] = num;
                            } else {
                                cmap[r][k] += num;
                                repeatCheck[r][k] = true;
                            }
                        }
                        break;
                    }
                }

                if (!isMeet) cmap[r][1] = num;

            }
        }
    }
}
