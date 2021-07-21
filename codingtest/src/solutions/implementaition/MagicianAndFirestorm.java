package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MagicianAndFirestorm {

    static int N;
    static int Q;
    static int[][] A;
    static int n;
    static int[] stages;
    static boolean[][] visited;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int sum = 0;
    static int max = 0;
    static int area = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        n = (int) Math.pow(2,N);
        A = new int[n][n];

        for (int i = 0;i<n;i++) {
            str = br.readLine();
            st = new StringTokenizer(str," ");
            for (int j = 0;j<n;j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        stages = new int[Q];
        str = br.readLine();
        st = new StringTokenizer(str," ");

        for (int i = 0;i<Q;i++) {
            stages[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0;i<Q;i++) {
            simulation(stages[i]);
        }

        visited = new boolean[n][n];

        for (int i = 0;i<n;i++) {
            for (int j = 0;j<n;j++) {
                if (A[i][j]!=0 && !visited[i][j]) {
                    area = 0;
                    dfs(i,j);
                }
            }
        }
        System.out.println(sum);
        System.out.println(max);
    }

    static void dfs(int r,int c) {
        visited[r][c] = true;
        area++;
        if (area>max) max = area;
        sum+=A[r][c];

        for (int i = 0;i<4;i++) {
            int nr = r+dr[i];
            int nc = c+dc[i];
            if (nr<0 || nr>=n || nc<0 || nc>=n) continue;
            if (A[nr][nc]!=0 && !visited[nr][nc]) {
                dfs(nr,nc);
            }
        }

    }

    static void simulation(int stage) {
        int m = (int) Math.pow(2,stage);

        int[][] temp = new int[n][n];

        for (int i = 0;i<n;i++) {
            for (int j = 0;j<n;j++) {
                temp[i][j] = A[i][j];
            }
        }

        //divide
        for (int i = 0;i<n/m;i++) {
            for (int j = 0;j<n/m;j++) {
                int r1 = i*m;
                int r2 = i*m+m-1;
                int c1 = j*m;
                int c2 = j*m+m-1;
                //rotation
                if (stage>0) {
                    temp = rotation(temp, m, r1, r2, c1, c2);
                }
            }
        }

        for (int i = 0;i<n;i++) {
            for (int j = 0;j<n;j++) {
                A[i][j] = temp[i][j];
            }
        }

        int[][] temp2 = new int[n][n];

        for (int i = 0;i<n;i++) {
            for (int j = 0;j<n;j++) {
                temp2[i][j] = A[i][j];
            }
        }

        for (int i = 0;i<n;i++) {
            for (int j = 0;j<n;j++) {
                if (A[i][j] == 0) continue;
                int cnt = 0;
                for (int d = 0;d<4;d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (nr < 0 || nr>=n || nc < 0 || nc>=n) continue;
                    if (A[nr][nc]!=0) cnt++;
                }
                if (cnt<3) temp2[i][j]--;
            }
        }

        for (int i = 0;i<n;i++) {
            for (int j = 0;j<n;j++) {
                A[i][j] = temp2[i][j];
            }
        }

    }

    static int[][] rotation(int[][] map, int m,int r1,int r2,int c1,int c2) {
        int[][] temp1 = new int[m][m];
        int[][] temp2 = new int[m][m];

        for (int i = 0;i<m;i++) {
            for (int j = 0;j<m;j++) {
                temp1[i][j] = A[r1+i][c1+j];
            }
        }

        for (int i = 0;i<m/2;i++) {
            int _r1 = i;
            int _r2 = m-i-1;
            int _c1 = i;
            int _c2 = m-i-1;
            for (int j = _c1;j<=_c2;j++) {
                temp2[j][_c2] = temp1[_r1][j];
            }
            for (int j = _r1;j<=_r2;j++) {
                temp2[_r2][_c2-(j-_r1)] = temp1[j][_c2];
            }
            for (int j = _c2;j>=_c1;j--) {
                temp2[j][_c1] = temp1[_r2][j];
            }
            for (int j = _r2;j>=_r1;j--) {
                temp2[_r1][_c2-(j-_r1)] = temp1[j][_c1];
            }
        }

        for (int i = r1;i<=r2;i++) {
            for (int j = c1;j<=c2;j++) {
                map[i][j] = temp2[i-r1][j-c1];
            }
        }

        return map;
    }
}
