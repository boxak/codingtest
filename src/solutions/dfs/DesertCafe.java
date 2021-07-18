package solutions.dfs;

import java.io.*;
import java.util.StringTokenizer;

public class DesertCafe {

    static int N;
    static int[][] map;
    static int[] dr = {-1,-1,1,1};
    static int[] dc = {-1,1,1,-1};
    static int answer;
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\Administrator\\Downloads\\sample_input (4).txt")));

        int TestCnt = Integer.parseInt(br.readLine());

        for (int test = 1;test<=TestCnt;test++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N+1][N+1];
            answer = 0;
            for (int i = 1;i<=100;i++) visited[i] = false;

            for (int i = 1;i<=N;i++) {
                String str = br.readLine();
                StringTokenizer st = new StringTokenizer(str," ");
                for (int j = 1;j<=N;j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 1;i<=N;i++) {
                for (int j = 1;j<=N;j++) {
                    for (int d = 0;d<4;d++) {
                        int nr = i+dr[d];
                        int nc = j+dc[d];
                        if (isOut(nr,nc)) continue;
                        //dfs에서 카운트 하기 전에 시작점에 왔는지를 봐야함
                        dfs(i,j,nr,nc,1,d,0);
                    }
                }
            }
            System.out.println("#"+test+" "+(answer==0 ? -1 : answer));

        }

    }

    static boolean isOut(int r,int c) {
        return r<1 || r>N || c<1 || c>N;
    }

    static void dfs(int sr,int sc,int r,int c,int count,int dir,int dirChangeCnt) {
        //System.out.println("sr : "+sr+" "+", sc : "+sc+", r : "+r+", c : "+c+", dir : "+dir);
        if (dirChangeCnt>3) return;
        if (dirChangeCnt==3 && r==sr && c==sc) {
            if (count>answer) answer = count;
            return;
        }
        visited[map[r][c]] = true;
        for (int d = 0;d<4;d++) {
            int nr = r+dr[d];
            int nc = c+dc[d];
            if (isOut(nr,nc)) continue;
            if (!visited[map[nr][nc]]) {
                if (d!=dir) dfs(sr,sc,nr,nc,count+1,d,dirChangeCnt+1);
                else dfs(sr,sc,nr,nc,count+1,d,dirChangeCnt);
            }
        }
        visited[map[r][c]] = false;
    }

}
