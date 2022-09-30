package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MagicianSharkCopy {

    static int M,S;
    static int[][] map;
    static int[][][] smells;
    static Queue<Fish> que;
    static Queue<Fish> copy;
    static Fish shark;
    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    static class Fish {
        int r;
        int c;
        int d;
        Fish(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");

        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        map = new int[5][5];
        smells = new int[3][5][5];
        que = new LinkedList<>();
        copy = new LinkedList<>();

        for (int i = 0;i<M;i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            que.add(new Fish(x, y, d));
        }

        str = br.readLine();
        st = new StringTokenizer(str, " ");

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        shark = new Fish(a, b, 0);

        for (int i = 0;i<S;i++) {
            doCopy();
            moveFish();
            moveShark();
            removeSmells();
            completeCopy();
//			print(i);
            cleanState();
        }

        int answer = que.size();

        System.out.println(answer);

    }

    static void print(int index) {
        System.out.println(index + " - shark : (" + shark.r + ", " + shark.c+")");

        int[][] temp = new int[5][5];

        for (Fish fish : que) {
            temp[fish.r][fish.c]++;
        }

        for (int i = 1;i<=4;i++) {
            for (int j = 1;j<=4;j++) {
                System.out.printf("%d ", temp[i][j]);
            }
            System.out.println();
        }

        System.out.println();
    }

    static void cleanState() {
        for (int i = 1;i<=4;i++) {
            for (int j = 1;j<=4;j++) {
                map[i][j] = 0;
            }
        }
    }

    static void doCopy() {
        for (Fish fish : que) {
            copy.add(new Fish(fish.r, fish.c, fish.d));
        }
    }

    static void moveFish() {
        int queSize = que.size();
        for (int i = 0;i<queSize;i++) {
            int r = que.peek().r;
            int c = que.peek().c;
            int d = que.peek().d;
            int dd = d;
            que.poll();
            boolean movable = false;

            for (int j = 1;j<=8;j++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (out(nr, nc) ||
                        (nr == shark.r && nc == shark.c) ||
                        (smells[0][nr][nc] > 0 || smells[1][nr][nc] > 0)) {
                    d = d == 1 ? 8 : d-1;
                } else {
                    movable = true;
                    que.add(new Fish(nr, nc, d));
                    map[nr][nc]+=1;
                    break;
                }

            }

            if (!movable) {
                que.add(new Fish(r, c, dd));
                map[r][c]+=1;
            }
        }
    }

    static void moveShark() {

        class Path implements Comparable<Path>{
            int pathNum;
            int fishCnt;
            Path(int a, int b) {
                pathNum = a;
                fishCnt = b;
            }
            @Override
            public int compareTo(Path o) {
                if (this.fishCnt == o.fishCnt) {
                    return this.pathNum - o.pathNum;
                }
                return o.fishCnt - this.fishCnt;
            }
        }

        int dr[] = {0, -1, 0, 1, 0};
        int dc[] = {0, 0, -1, 0, 1};

        List<Path> paths = new ArrayList<Path>();

        for (int d1 = 1; d1<=4; d1++) {
            int nr1 = shark.r + dr[d1];
            int nc1 = shark.c + dc[d1];
            if (out(nr1, nc1)) continue;
            for (int d2 = 1; d2<=4;d2++) {
                int nr2 = nr1 + dr[d2];
                int nc2 = nc1 + dc[d2];
                if (out(nr2, nc2)) continue;
                for (int d3 = 1;d3<=4;d3++) {
                    int nr3 = nr2 + dr[d3];
                    int nc3 = nc2 + dc[d3];
                    if (out(nr3, nc3)) {
                        continue;
                    }
                    int pathNum = Integer.parseInt(d1+""+d2+""+d3);
                    int fishCnt = map[nr1][nc1] + map[nr2][nc2] + map[nr3][nc3];
                    if (nr1 == nr2 && nc1 == nc2) {
                        fishCnt = map[nr1][nc1] + map[nr3][nc3];
                    }
                    if (nr1 == nr3 && nc1 == nc3) {
                        fishCnt = map[nr1][nc1] + map[nr2][nc2];
                    }
                    if (nr2 == nr3 && nc2 == nc3) {
                        fishCnt = map[nr1][nc1] + map[nr3][nc3];
                    }
                    paths.add(new Path(pathNum, fishCnt));
                }
            }
        }

        Collections.sort(paths);

        if (!paths.isEmpty()) {
            Path path = paths.get(0);
            for (int i = 0;i<3;i++) {
                int d = (int)(Integer.toString(path.pathNum).charAt(i) - '0');
                shark.r = shark.r + dr[d];
                shark.c = shark.c + dc[d];
                if (map[shark.r][shark.c] >= 1) {
                    smells[2][shark.r][shark.c] = 1;
                    map[shark.r][shark.c] = 0;
                }
            }
        }

        int queSize = que.size();

        for (int i = 0;i<queSize;i++) {
            int r = que.peek().r;
            int c = que.peek().c;
            int d = que.peek().d;
            que.poll();

            if (map[r][c] >= 1) {
                que.add(new Fish(r, c, d));
            }
        }

    }

    static void removeSmells() {
        for (int i = 0;i<2;i++) {
            for (int j = 1;j<=4;j++) {
                for (int k = 1;k<=4;k++) {
                    smells[i][j][k] = smells[i+1][j][k];
                }
            }
        }

        for (int i = 1;i<=4;i++) {
            for (int j = 1;j<=4;j++) {
                smells[2][i][j] = 0;
            }
        }
    }

    static void completeCopy() {
        while(!copy.isEmpty()) {
            int r = copy.peek().r;
            int c = copy.peek().c;
            int d = copy.peek().d;

            copy.poll();

            que.add(new Fish(r, c, d));
        }
    }

    static boolean out(int r, int c) {
        return r < 1 || r > 4 || c < 1 || c > 4;
    }
}
