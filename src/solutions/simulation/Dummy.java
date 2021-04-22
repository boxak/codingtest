package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 복습이 필요한 문제!
public class Dummy {

    static int[][] map;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static ArrayList<Pair> snake;
    static ArrayList<Pair> snake2;
    static int N,K,L;
    static int[] changes;
    static int answer;
    static boolean finish;
    static boolean isEat;

    static class Pair {
        int r;
        int c;
        int d;
        Pair(int r,int c,int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        answer = 0;
        finish = false;

        map = new int[N+1][N+1];
        snake = new ArrayList<>();
        snake2 = new ArrayList<>();
        changes = new int[10010];

        for (int i = 0;i<K;i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");
            int apple_r = Integer.parseInt(st.nextToken());
            int apple_c = Integer.parseInt(st.nextToken());
            map[apple_r][apple_c] = 2;
        }

        L = Integer.parseInt(br.readLine());

        for (int i = 0;i<L;i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");
            int time = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            if ("L".equals(dir)) changes[time] = -1;
            else changes[time] = 1;
        }

        snake.add(new Pair(1,1,1));

        while(true) {
            answer++;
            moveHead(answer);
            if (finish) break;
            moveBody();
        }
        System.out.println(answer);
    }

    static void moveHead(int time) {
        Pair pair = snake.get(0);
        int r = pair.r;
        int c = pair.c;
        int d = pair.d;
        snake2.clear();
        isEat = false;

        int nr = r+dr[d];
        int nc = c+dc[d];

        if (changes[time]!=0) {
            d = changeDir(d, changes[time]);
        }
        snake2.add(new Pair(nr,nc,d));
        if (nr < 1 || nr>N || nc<1 || nc>N) {
            finish = true;
            return;
        }
        if (map[nr][nc] == 2) {
            isEat = true;
            map[nr][nc] = 0;
        }
    }

    static void moveBody() {
        for (int i = 1;i<snake.size();i++) {
            Pair pair1 = snake.get(i-1);

            snake2.add(new Pair(pair1.r,pair1.c,pair1.d));
        }
        if (isEat) {
            Pair pair1 = snake.get(snake.size()-1);
            snake2.add(new Pair(pair1.r,pair1.c,pair1.d));
        }
        snake.clear();
        for (int i = 0;i<snake2.size();i++) {
            int r = snake2.get(i).r;
            int c = snake2.get(i).c;
            int d = snake2.get(i).d;

            if (map[r][c] == 1) {
                finish = true;
                break;
            }
            map[r][c] = 1;
            snake.add(new Pair(r,c,d));
        }

        System.out.println(answer);
        for (int i = 1;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                System.out.printf("%d ",map[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 1;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                if (map[i][j]!=2) {
                    map[i][j] = 0;
                }
            }
        }
    }

    static int changeDir(int d, int gubun) {
        if (gubun == -1) {
            d = d==0 ? 3 : d-1;
        } else {
            d = (d+1)%4;
        }
        return d;
    }
}
