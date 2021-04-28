package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SharkClass {

    static int N;
    static int[][] friends;
    static int answer;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int[][] map;
    static int[] scores = {0,1,10,100,1000};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        friends = new int[N*N+1][5];
        answer = 0;
        for (int i = 1;i<=N*N;i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");

            for (int j = 0;j<5;j++) {
                friends[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int f = 1;f<=N*N;f++) {
            int studentNum = friends[f][0];
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 1;j<=4;j++) list.add(friends[f][j]);

            int[][] temp = new int[N+1][N+1];
            int maxCnt = 0;

            for (int i = 1;i<=N;i++) {
                for (int j = 1;j<=N;j++) {
                    if (map[i][j]!=0) continue;
                    int cnt = 0;
                    for (int d = 0;d<4;d++) {
                        int nr = i+dr[d];
                        int nc = j+dc[d];
                        if (nr<1 || nr>N || nc<1 || nc>N) continue;
                        if (list.contains(map[nr][nc])) {
                            cnt++;
                        }
                    }
                    if (maxCnt < cnt) maxCnt = cnt;
                    temp[i][j] = cnt;
                }
            }

            int repeatCnt = 0;
            int r1 = -1;
            int c1 = -1;
            for (int i = 1;i<=N;i++) {
                for (int j = 1;j<=N;j++) {
                    if (maxCnt == temp[i][j]) {
                        repeatCnt++;
                        r1 = i;
                        c1 = j;
                    }
                }
            }

            if (repeatCnt == 1) {
                map[r1][c1] = studentNum;
            } else {
                int[][] temp2 = new int[N+1][N+1];
                int maxCnt2 = 0;
                for (int i = 1;i<=N;i++) {
                    for (int j = 1;j<=N;j++) {
                        if (map[i][j]!=0) continue;
                        if (temp[i][j] != maxCnt) continue;
                        int cnt = 0;
                        for (int d = 0;d<4;d++) {
                            int nr = i+dr[d];
                            int nc = j+dc[d];
                            if (nr<1 || nr>N || nc<1 || nc>N) continue;
                            if (map[nr][nc] == 0) cnt++;
                        }
                        if (maxCnt2<cnt) maxCnt2 = cnt;
                        temp2[i][j] = cnt;
                    }
                }

                for (int i = 1;i<=N;i++) {
                    boolean flag = false;
                    for (int j = 1;j<=N;j++) {
                        if (map[i][j]!=0) continue;
                        if (maxCnt2 == temp2[i][j] && temp[i][j] == maxCnt) {
                            map[i][j] = studentNum;
                            flag = true;
                            break;
                        }
                    }
                    if (flag) break;
                }

            }

//            for (int i = 1;i<=N;i++) {
//                for (int j = 1;j<=N;j++) {
//                    System.out.printf("%d ",map[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println();
        }

        for (int i = 1;i<=N;i++) {
            for (int j = 1;j<=N;j++) {
                int studentNum = map[i][j];
                int inx = -1;
                ArrayList<Integer> list = new ArrayList<>();
                for (int f = 1;f<=N*N;f++) {
                    if (friends[f][0]==studentNum) {
                        inx = f;
                        for (int k = 1;k<=4;k++) list.add(friends[f][k]);
                        break;
                    }
                }
                int cnt = 0;
                for (int d = 0;d<4;d++) {
                    int nr = i+dr[d];
                    int nc = j+dc[d];
                    if (nr<1 || nr>N || nc<1 || nc>N) continue;
                    if (list.contains(map[nr][nc])) cnt++;
                }
                answer+=scores[cnt];
            }
        }
        System.out.println(answer);
    }
}
