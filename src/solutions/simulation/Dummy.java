package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 복습이 필요한 문제!
public class Dummy {

    static int N,K,L;
    static int[][] map;
    static int[] changeDirs;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static ArrayList<int[]> snake;
    static int dir;
    static boolean isEnd = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        snake = new ArrayList<>();

        for (int i = 0;i<K;i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 2;
        }

        L = Integer.parseInt(br.readLine());
        changeDirs = new int[10010];

        for (int i = 0;i<L;i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");
            int X = Integer.parseInt(st.nextToken());
            String c = st.nextToken();
            if ("L".equals(c)) {
                changeDirs[X] = -1;
            } else {
                changeDirs[X] = 1;
            }
        }

        dir = 1;
        snake.add(new int[]{1,1});
        map[1][1] = 1;

        int time = 0;
        while(!isEnd) {
            time++;
            simulation(time);
            //draw(time);
        }

        System.out.println(time);
    }

    static void draw(int time) {
        System.out.println("#time : "+time+", dir : "+dir);
        for (int i = 1;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                System.out.printf("%d ",map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void simulation(int time) {
        boolean eatApple = false;
        int lastR = snake.get(0)[0];
        int lastC = snake.get(0)[1];

        int nhr = lastR + dr[dir];
        int nhc = lastC + dc[dir];

        if (nhr<1 || nhr>N || nhc<1 || nhc>N) {
            isEnd = true;
        } else if (map[nhr][nhc]==1) {
            isEnd = true;
        }
        if (isEnd) return;

        snake.set(0,new int[]{nhr,nhc});
        map[lastR][lastC] = 0;

        for (int i = 1;i<snake.size();i++) {
            int[] point = snake.get(i);
            int r = point[0];
            int c = point[1];

            int nr = lastR;
            int nc = lastC;
            lastR = r;
            lastC = c;

            map[r][c] = 0;
            snake.set(i,new int[]{nr,nc});
        }

        for (int i = 0;i<snake.size();i++) {
            int[] point = snake.get(i);
            int r = point[0];
            int c = point[1];

            if (map[r][c]==2) {
                eatApple = true;
                map[r][c] = 1;
            } else if (map[r][c]==1) {
                isEnd = true;
                break;
            } else {
                map[r][c] = 1;
            }
        }

        if (eatApple) {
            map[lastR][lastC] = 1;
            snake.add(new int[]{lastR,lastC});
        }

        if (changeDirs[time]!=0) {
            if (changeDirs[time]==-1) {
                dir = dir == 0 ? 3 : dir-1;
            } else {
                dir = (dir+1)%4;
            }
        }
    }

}
