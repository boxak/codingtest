package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 복습이 필요한 문제!
public class Dummy {

    static int N,K;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int[][] map;
    static int answer;
    static ArrayList<Pair> snake;
    static int dir;
    static int L;
    static ArrayList<Dir> changeDir;

    static class Dir {
        int time;
        String dir;
        Dir(int x,String y) {
            time = x;
            dir = y;
        }
    }

    static class Pair {
        int r;
        int c;
        Pair(int x,int y) {
            r = x;
            c = y;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        snake = new ArrayList<>();
        answer = 0;
        changeDir = new ArrayList<>();

        for (int i = 0;i<K;i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = 2;
        }

        int L = Integer.parseInt(br.readLine());

        for (int i = 0;i<L;i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");
            int x = Integer.parseInt(st.nextToken());
            String y = st.nextToken();
            changeDir.add(new Dir(x,y));
        }

        snake.add(new Pair(1,1));
        map[1][1] = 1;
        dir = 1;
        boolean eatApple = false;
        int inx = 0;

        while(true) {
            answer++;
            eatApple = false;
            int hr = snake.get(0).r;
            int hc = snake.get(0).c;

            int nhr = hr+dr[dir];
            int nhc = hc+dc[dir];

            if (nhr<1 || nhr>N || nhc<1 || nhc>N) {
                break;
            } else if (map[nhr][nhc] == 1) break;

            if (map[nhr][nhc] == 2) {
                map[nhr][nhc] = 0;
                eatApple = true;
            }
            ArrayList<Pair> temp = new ArrayList<>();
            map[nhr][nhc] = 1;
            map[hr][hc] = 0;
            temp.add(new Pair(nhr,nhc));

            if (answer == changeDir.get(inx).time) {
                if ("L".equals(changeDir.get(inx).dir)) {
                    dir--;
                    if (dir == -1) dir = 3;
                } else {
                    dir = (dir+1)%4;
                }
                inx = inx==changeDir.size()-1 ? inx : inx+1;
            }

            int lastR = hr;
            int lastC = hc;

            for (int i = 1;i<snake.size();i++) {
                int r = snake.get(i-1).r;
                int c = snake.get(i-1).c;

                int rr = snake.get(i).r;
                int cc = snake.get(i).c;

                if (i == snake.size()-1){
                    lastR = rr;
                    lastC = cc;
                }

                temp.add(new Pair(r,c));
                map[rr][cc] = 0;
                map[r][c] = 1;
            }

            if (eatApple) {
                temp.add(new Pair(lastR,lastC));
                map[lastR][lastC] = 1;
            }

            snake.clear();
            for (Pair pair : temp) snake.add(new Pair(pair.r,pair.c));

//            System.out.println(answer);
//            for (int i = 1;i<=N;i++) {
//                for (int j = 1;j<=N;j++) {
//                    System.out.printf("%d ",map[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();

        }

        System.out.println(answer);
    }
}
