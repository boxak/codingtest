package solutions.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LadderControl {

    static int answer = -1;
    static boolean finish = false;
    static int connected[][];
    static int N,M,H;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        connected = new int[H+1][N];

        for (int i = 0;i<M;i++) {
            str = br.readLine();
            st = new StringTokenizer(str," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connected[a][b] = 1;
        }

        for (int i = 0;i<=3;i++) {
            dfs(1, 1, i, 0);
            if (finish) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

    static void dfs(int a,int b,int limit, int cnt) {
        if (cnt == limit) {
            simulation();
            return;
        }
        if (b>=N) return;
        if (a>H) dfs(1,b+1,limit,cnt);
        else {
            if (connected[a][b] == 1) {
                dfs(a + 1, b, limit, cnt);
            } else {
                if (b == 1 && N>2) {
                    if (connected[a][b + 1] == 0) {
                        connected[a][b] = 1;
                        dfs(a + 1, b, limit, cnt + 1);
                    }
                } else if (b == N - 1 && N>2) {
                    if (connected[a][b - 1] == 0) {
                        connected[a][b] = 1;
                        dfs(a + 1, b, limit, cnt + 1);
                    }
                } else {
                    if (N>2) {
                        if (connected[a][b - 1] == 0 && connected[a][b + 1] == 0) {
                            connected[a][b] = 1;
                            dfs(a + 1, b, limit, cnt + 1);
                        }
                    } else {
                        connected[a][b] = 1;
                        dfs(a+1,b,limit,cnt+1);
                    }
                }
                connected[a][b] = 0;
                dfs(a + 1, b, limit, cnt);
            }
        }
    }

    static void simulation() {
        boolean flag = true;
        for (int j = 1;j<=N;j++) {
            int r = 1;
            int c = j;
            while(r<=H) {
                if (c == 1) {
                    if (connected[r][c] == 0) {
                        r++;
                    } else {
                        r++;
                        c++;
                    }
                } else if (c == N) {
                    if (connected[r][c-1] == 0) {
                        r++;
                    } else {
                        r++;
                        c--;
                    }
                } else {
                    if (connected[r][c] == 0 && connected[r][c-1] == 0) {
                        r++;
                    } else if(connected[r][c] == 1) {
                        r++;
                        c++;
                    } else if (connected[r][c-1] == 1) {
                        r++;
                        c--;
                    }
                }
            }
            if (c != j) {
                flag = false;
                break;
            }
        }
        if (flag) {
            finish = true;
        }
    }
}
