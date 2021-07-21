package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MagicSharkAndFireball {

    static int N;
    static int[][] A;
    static int[] ls;
    static int iceCnt;
    static int maxCnt;
    static boolean[][] visited;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        int M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        ls = new int[Q];

        N = (int)Math.pow(2,M);
        A = new int[N][N];

        for (int i = 0;i<N;i++) {
            str = br.readLine();
            st = new StringTokenizer(str," ");
            for (int j = 0;j<N;j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        str = br.readLine();
        st = new StringTokenizer(str," ");
        for (int i = 0;i<Q;i++) {
            ls[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0;i<Q;i++) {
            simulation(ls[i]);
        }

        int sum = 0;
        iceCnt = 0;
        maxCnt = 0;
        visited = new boolean[N][N];
        for (int i = 0;i<N;i++) {
            for (int j = 0;j<N;j++) {
                sum+=A[i][j];
            }
        }

        for (int i = 0;i<N;i++) {
            for (int j = 0;j<N;j++) {
                if (!visited[i][j] && A[i][j]>0) {
                    iceCnt = 0;
                    dfs(i,j);
                    if (iceCnt>maxCnt) {
                        maxCnt = iceCnt;
                    }
                }
            }
        }

        System.out.println(sum);
        System.out.println(maxCnt);
    }

    static void dfs(int r,int c) {
        visited[r][c] = true;
        iceCnt++;
        for (int i = 0;i<4;i++) {
            int nr = r+dr[i];
            int nc = c+dc[i];
            if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
            if (!visited[nr][nc] && A[nr][nc]>0) {
                dfs(nr,nc);
            }
        }
    }

    static void simulation(int L) {
        int n = (int)Math.pow(2,L);
        if (L > 0) {
            for (int i = 0; i <= N - n; i += n) {
                for (int j = 0; j <= N - n; j += n) {
                    int[][] temp = new int[n][n];
                    for (int k = i; k < i + n; k++) {
                        for (int f = j; f < j + n; f++) {
                            temp[k - i][f - j] = A[k][f];
                        }
                    }
                    temp = rotation(temp, n, L);
                    for (int k = i; k < i + n; k++) {
                        for (int f = j; f < j + n; f++) {
                            A[k][f] = temp[k - i][f - j];
                        }
                    }
                }
            }
        }
        melt();
    }

    static int[][] rotation(int[][] arr,int n,int L) {
        int[][] temp = new int[n][n];
        for (int i = 0;i<L;i++) {
            for (int j=i;j<n-i;j++) {
                temp[i][j] = arr[n-j-1][i];
            }

            for (int j=i;j<n-i;j++) {
                temp[j][n-i-1] = arr[i][j];
            }

            for (int j=0;j<n-i;j++) {
                temp[n-i-1][j] = arr[n-j-1][n-i-1];
            }

            for (int j=0;j<n-i;j++) {
                temp[j][i] = arr[n-i-1][j];
            }
        }

        return temp;
    }

    static void melt() {
        int[][] B = A;
        for (int i = 0;i<N;i++) {
            for (int j = 0;j<N;j++) {
                int cnt = 0;
                for (int dir = 0;dir<4;dir++) {
                    int nr = i+dr[dir];
                    int nc = j+dc[dir];
                    if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
                    if (A[nr][nc]>0) cnt++;
                }
                if (cnt<3 && A[i][j]>0) {
                    B[i][j]--;
                }
            }
        }
        A = B;
    }

}
