package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 복습이 필요한 문제!
public class Dummy {

    static class Pair {
        int time;
        String dir;

        Pair(int x, String y) {
            time = x;
            dir = y;
        }
    }

    static class Snake {
        int r;
        int c;

        Snake(int x, int y) {
            r = x;
            c = y;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int map[][] = new int[110][110];
        int dr[] = {-1, 0, 1, 0};
        int dc[] = {0, 1, 0, -1};

        for (int i = 0; i < K; i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            int apple_r = Integer.parseInt(st.nextToken());
            int apple_c = Integer.parseInt(st.nextToken());
            map[apple_r][apple_c] = 2;
        }

        int L = Integer.parseInt(br.readLine());
        ArrayList<Pair> changeDirList = new ArrayList<>();
        for (int i = 0; i < L; i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            int time = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            changeDirList.add(new Pair(time, dir));
        }

        int answer = 0;
        int inx = 0;
        int hdir = 1;

        ArrayList<Snake> snake = new ArrayList<>();
        snake.add(new Snake(1, 1));

        while (true) {
            answer++;
            int r = snake.get(0).r;
            int c = snake.get(0).c;

            int nr = r + dr[hdir];
            int nc = c + dc[hdir];

            if (nr < 1 || nr > N || nc < 1 || nc > N) {
                break;
            } else if (map[nr][nc] == 1) {
                break;
            }

            boolean isApple = false;
            if (map[nr][nc] == 2) {
                isApple = true;
            }

            snake.set(0, new Snake(nr, nc));

            int snakeLen = snake.size();

            boolean isStop = false;
            for (int i = 1; i < snakeLen; i++) {
                nr = r;
                nc = c;
                r = snake.get(i).r;
                c = snake.get(i).c;
                map[r][c] = 0;
                map[nr][nc] = 1;

                snake.set(i, new Snake(nr, nc));
            }

            if (isApple) {
                int lastr = r;
                int lastc = c;
                map[lastr][lastc] = 1;
                snake.add(new Snake(lastr, lastc));
            }

            if (answer == changeDirList.get(inx).time) {
                if ("L".equals(changeDirList.get(inx).dir)) {
                    hdir = hdir == 0 ? 3 : hdir - 1;
                } else {
                    hdir = (hdir + 1) % 4;
                }
                inx = inx == (changeDirList.size() - 1) ? inx : inx + 1;
            }

        }
        System.out.println(answer);
    }
}
