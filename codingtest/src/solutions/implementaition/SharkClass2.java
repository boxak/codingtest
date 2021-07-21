package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SharkClass2 {
    static int N,M;
    static int[][] map;
    static int answer;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static boolean finish;
    static int blockCnt;
    static int rainbowCnt;
    static boolean[][] visited;
    static int rr,cc;

    static class Pair implements Comparable<Pair> {
        int area;
        int rainbow;
        int r;
        int c;

        Pair(int area,int rainbow,int r,int c) {
            this.area = area;
            this.rainbow = rainbow;
            this.r = r;
            this.c = c;
        }

        public int compareTo(Pair pair) {
            if (this.area == pair.area) {
                if (this.rainbow == pair.rainbow) {
                    if (this.r == pair.r) {
                        return pair.c-this.c;
                    } else return pair.r-this.r;
                } else return pair.rainbow-this.rainbow;
            } else return pair.area-this.area;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        finish = false;
        answer = 0;
        rr = -1;
        cc = -1;
        blockCnt = 0;
        rainbowCnt = 0;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1];

        for (int i = 1;i<=N;i++) {
            str = br.readLine();
            st = new StringTokenizer(str," ");
            for (int j = 1;j<=N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //System.out.println();
        while(true) {
            findNumber();
            if (finish) break;
            cleanVisit(false);
            remove(map[rr][cc],rr,cc);
            cleanVisit(false);
            //draw();
            fallDown();
            //draw();
            rotate();
            //draw();
            fallDown();
            //draw();
        }

        System.out.println(answer);
    }

    static void findNumber() {
        ArrayList<Pair> list = new ArrayList<>();
        for (int i = 1;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                if (map[i][j]>0 && !visited[i][j]) {
                    cleanVisit(true);
                    blockCnt = 0;
                    rainbowCnt = 0;
                    dfs(map[i][j],i,j);
                    //System.out.println();
                    if (blockCnt<2) continue;
                    list.add(new Pair(blockCnt,rainbowCnt,i,j));
                }
            }
        }
        if (list.size() == 0) {
            finish = true;
            return;
        }
        Collections.sort(list);
        rr = list.get(0).r;
        cc = list.get(0).c;
        //System.out.println("map[rr][cc] : "+map[rr][cc]);
        answer+=list.get(0).area*list.get(0).area;
    }

    static void cleanVisit(boolean flag) {
        if (!flag) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    visited[i][j] = false;
                }
            }
        } else {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][j]==0) {
                        visited[i][j] = false;
                    }
                }
            }
        }
    }

    static void dfs(int num,int r,int c) {
        //System.out.println(r+" "+c+" "+map[r][c]);
        visited[r][c] = true;
        blockCnt++;
        if (map[r][c]==0) rainbowCnt++;
        for (int d = 0;d<4;d++) {
            int nr = r+dr[d];
            int nc = c+dc[d];
            if (nr<1 || nr>N || nc<1 || nc>N) continue;
            if ((map[nr][nc]==num || map[nr][nc]==0) && !visited[nr][nc]) {
                dfs(num,nr,nc);
            }
        }
    }

    static void remove(int num,int r,int c) {
        visited[r][c] = true;
        map[r][c] = -2;
        for (int d = 0;d<4;d++) {
            int nr = r+dr[d];
            int nc = c+dc[d];
            if (nr<1 || nr>N || nc<1 || nc>N) continue;
            if ((map[nr][nc]==num || map[nr][nc]==0) && !visited[nr][nc]) {
                remove(num,nr,nc);
            }
        }
    }

    static void fallDown() {
        int[][] temp = new int[N+1][N+1];
        for (int i = 1;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                temp[i][j] = -2;
                if (map[i][j]==-1) temp[i][j] = -1;
            }
        }

        for (int i = N;i>=1;i--) {
            for (int j = 1;j<=N;j++) {
                if (map[i][j]==-1 || map[i][j]==-2) continue;
                int num = map[i][j];
                int row = i;
                int col = j;

                while (true) {
                    if (row+1>N || temp[row+1][col]>-2) break;
                    row++;
                }
                temp[row][col] = num;
            }
        }

        for (int i = 1;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    static void rotate() {
        int[][] temp = new int[N+1][N+1];

        for (int i = 1;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                temp[i][j] = map[j][N-i+1];
            }
        }

        for (int i = 1;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                map[i][j] = temp[i][j];
            }
        }

    }

    static void draw() {
        for (int i = 1;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                System.out.printf("%d ",map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
