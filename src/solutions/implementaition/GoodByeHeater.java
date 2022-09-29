package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GoodByeHeater {

    static int R,C,K, W;
    static int map[][];
    static int dr[] = {-1,-1,-1,0,1,1,1,0};
    static int dc[] = {-1,0,1,1,1,0,-1,-1};

    // 0 : 위에 벽, 1 : 아래에 벽, 2 : 오른쪽에 벽, 3 : 왼쪽에 벽
    static boolean walls[][][];
    static List<Fan> fans;
    static List<Pair> checks;
    static Map<Integer, List<Pair>> moves;

    static class Fan {
        int r;
        int c;
        int d;
        Fan(int r, int c, int d) {
            this.r =r ;
            this.c = c;
            this.d = d;
        }
    }

    static class Pair {
        int r;
        int c;
        Pair (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int answer = 0;

        map = new int[R+1][C+1];
        walls = new boolean[5][R+1][C+1];
        fans = new ArrayList<>();
        checks = new ArrayList<>();
        moves = new HashMap<>();

        List<Pair> rightMove = new ArrayList<>();
        rightMove.add(new Pair(-1,1));
        rightMove.add(new Pair(0,1));
        rightMove.add(new Pair(1,1));

        List<Pair> leftMove = new ArrayList<>();
        leftMove.add(new Pair(-1,-1));
        leftMove.add(new Pair(0,-1));
        leftMove.add(new Pair(1,-1));

        List<Pair> upMove = new ArrayList<>();
        upMove.add(new Pair(-1,-1));
        upMove.add(new Pair(-1,0));
        upMove.add(new Pair(-1,1));

        List<Pair> downMove = new ArrayList<>();
        downMove.add(new Pair(1,-1));
        downMove.add(new Pair(1,0));
        downMove.add(new Pair(1,1));

        moves.put(1, rightMove);
        moves.put(2, leftMove);
        moves.put(3, upMove);
        moves.put(4, downMove);

        for (int i = 1;i<=R;i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            for (int j = 1;j<=C;j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num > 0 && num < 5) {
                    fans.add(new Fan(i,j,num));
                } else if (num == 5) {
                    checks.add(new Pair(i,j));
                }
            }
        }

        W = Integer.parseInt(br.readLine());

        for (int i = 0;i<W;i++) {
            str = br.readLine();
            st = new StringTokenizer(str," ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if (t == 0) {
                walls[3][x][y] = true;
                if (x - 1 >= 1) walls[4][x - 1][y] = true;
            } else {
                walls[1][x][y] = true;
                if (y+1<=C) walls[2][x][y+1] = true;
            }
        }

        while(true) {
            wind();
            temperatureSpreadOut();
            minusOne();
            answer++;

            if (overK() || answer == 101) {
                break;
            }
        }

        System.out.println(answer);
    }

    static void print() {
        for (int i = 1;i<=R;i++) {
            for (int j = 1;j<=C;j++) {
                System.out.printf("%d ",map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean overK() {
        boolean flag = true;
        for (Pair pair : checks) {
            if (map[pair.r][pair.c] < K) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    static void minusOne() {
        for (int j = 1;j<=C;j++) {
            if (map[1][j] >= 1) map[1][j]--;
            if (map[R][j]>=1) map[R][j]--;
        }

        for (int i = 2;i<=R-1;i++) {
            if (map[i][1]>=1) map[i][1]--;
            if (map[i][C]>=1) map[i][C]--;
        }
    }

    static void temperatureSpreadOut() {
        int[][] tempMap = new int[R+1][C+1];

        for (int i = 1;i<=R;i++) {
            for (int j = 1;j<=C;j++) {
                int r = i;
                int c = j;
                for (int d = 1;d<=4;d++) {
                    int nr = 0;
                    int nc = 0;
                    if (d == 1) {
                        nr = r;
                        nc = c+1;
                    } else if (d == 2) {
                        nr = r;
                        nc = c-1;
                    } else if (d==3) {
                        nr = r-1;
                        nc = c;
                    } else if (d==4) {
                        nr = r+1;
                        nc = c;
                    }

                    if (out(nr, nc)) continue;
                    if (movable(r, c, nr, nc, d) && map[r][c] > map[nr][nc]) {
                        int difference = map[r][c] - map[nr][nc];
                        tempMap[r][c]-=difference/4;
                        tempMap[nr][nc]+=difference/4;
                    }
                }
            }
        }

        for (int i = 1;i<=R;i++) {
            for (int j = 1;j<=C;j++) {
                map[i][j]+=tempMap[i][j];
            }
        }

    }

    static void wind() {
        int[][] increase = new int[R+1][C+1];
        boolean[][] visited = new boolean[R+1][C+1];
        Queue<Pair> que = new LinkedList<>();

        for (int index = 0;index< fans.size();index++) {
            Fan fan = fans.get(index);
            int fanR = fan.r;
            int fanC = fan.c;
            int fanD = fan.d;
            int fanD2 = 0;
            List<Pair> move = moves.get(fanD);
            if (fanD == 1) fanD2 = 3;
            else if (fanD == 2) fanD2 = 7;
            else if (fanD == 3) fanD2 = 1;
            else if (fanD == 4) fanD2 = 5;

            int sr = fanR + dr[fanD2];
            int sc = fanC + dc[fanD2];
            if (out(sr, sc)) continue;

            increase[sr][sc]+= 5;
            visited[sr][sc] = true;
            que.add(new Pair(sr, sc));

            for (int i = 4;i>=1;i--) {
                int queSize = que.size();
                for (int j = 0;j<queSize;j++) {
                    int r = que.peek().r;
                    int c = que.peek().c;
                    que.poll();
                    for (int k = 0;k<move.size();k++) {
                        int nr = r + move.get(k).r;
                        int nc = c + move.get(k).c;
                        if (out(nr, nc)) continue;
                        if (!visited[nr][nc] && movable(r, c, nr, nc, fanD)) {
                            visited[nr][nc] = true;
                            increase[nr][nc]+=i;
                            que.add(new Pair(nr, nc));
                        }
                    }
                }
            }

            que.clear();

            for (int i = 1;i<=R;i++) {
                for (int j = 1;j<=C;j++) {
                    visited[i][j] = false;
                }
            }

        }

        for (int i = 1;i<=R;i++) {
            for (int j = 1;j<=C;j++) {
                map[i][j]+=increase[i][j];
            }
        }

    }

    static boolean out(int r, int c) {
        return r < 1 || r > R || c < 1 || c>C;
    }

    static boolean movable(int r1, int c1, int r2, int c2, int fanD) {
        boolean pass = false;
        if (fanD == 1) {
            if (r1 == r2) {
                pass = !walls[1][r1][c1];
            } else if (r1 > r2) {
                pass = !walls[3][r1][c1] && !walls[2][r2][c2];
            } else if (r1 < r2) {
                pass = !walls[4][r1][c1] && !walls[2][r2][c2];
            }
        } else if (fanD == 2) {
            if (r1 == r2) {
                pass = !walls[2][r1][c1];
            } else if (r1 > r2) {
                pass = !walls[3][r1][c1] && !walls[1][r2][c2];
            } else if (r1 < r2) {
                pass = !walls[4][r1][c1] && !walls[1][r2][c2];
            }
        } else if (fanD == 3) {
            if (c1 == c2) {
                pass = !walls[3][r1][c1];
            } else if (c1 > c2) {
                pass = !walls[2][r1][c1] && !walls[4][r2][c2];
            } else if (c1 < c2) {
                pass = !walls[1][r1][c1] && !walls[4][r2][c2];
            }
        } else if (fanD == 4) {
            if (c1 == c2) {
                pass = !walls[4][r1][c1];
            } else if (c1 > c2) {
                pass = !walls[2][r1][c1] && !walls[3][r2][c2];
            } else if (c1 < c2) {
                pass = !walls[1][r1][c1] && !walls[3][r2][c2];
            }
        }
        return pass;
    }
}
