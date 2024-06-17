package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CleaningFishbowl {
    // 1. 물고기 수가 가장 적은 어항에 물고기를 한마리 넣는다.
    // 2. 2개 이상 쌓인 어항들에 대해 공중부양 시킨 후 시계방향 90도 회전을 실시
    // 2번 작업은 진행할 수 없을 때 가지 반복
    // 3. 물고기 수 조절
    // 4. 바닥에 일렬로 정렬
    // 5. 왼쪽 N/2 개를 시계방향으로 180도 회전시켜서 공중부양 2번 실시
    // 6. 물고기 수 조절
    // 7. 바닥에 일렬로 정렬

    static int N,K;
    static int[][] map;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};//

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        str = br.readLine();
        st = new StringTokenizer(str," ");
        map = new int[100][100];

        for (int i = 0;i<=99;i++) {
            for (int j = 0;j<=99;j++) {
                map[i][j] = -1;
            }
        }

        for (int i = 0;i<N;i++) {
            map[0][i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;

        while(!check()) {
            inputFish();
            arrange1();
            controlNumber();
            sort();
            arrange2();
            controlNumber();
            sort();
        }
        System.out.println(answer);
    }

    static boolean check() {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0;i<100;i++) {
            for (int j = 0;j<100;j++) {
                if (map[i][j] != -1 && map[i][j] > max) {
                    max = map[i][j];
                }
                if (map[i][j]!=-1 && map[i][j] < min) {
                    min = map[i][j];
                }
            }
        }

        return max - min < K;
    }

    static void inputFish() {
        int min = Integer.MAX_VALUE;
        for (int i = 0;i<100;i++) {
            for (int j = 0;j<100;j++) {
                if (map[i][j]!=-1 && map[i][j] < min) {
                    min = map[i][j];
                }
            }
        }

        for (int i = 0;i<100;i++) {
            for (int j = 0;j<100;j++) {
                if (map[i][j]!=-1 && map[i][j] == min) {
                    map[i][j]++;
                }
            }
        }
    }

    static void arrange1() {
        int leftNum = map[0][0];

        map[0][0] = 0;
        map[1][1] = leftNum;

        moveAllLeft();

        while(!arrange1Stop()) {
            moveAndRotate();
            moveAllLeft();
        }

    }

    static void moveAllLeft() {
        if (map[0][99]!=0) return;
        for (int j = 1;j<=99;j++) {
            for (int i = 0;i<=99;i++) {
                map[i-1][j] = map[i][j];
            }
        }
    }

    static boolean arrange1Stop() {
        int height = 0;
        int lengthOf1Height = 0;

        for (int j = 99;j>=0;j--) {
            if (map[0][j]!=-1) {
                if (map[1][j]==-1) {
                    lengthOf1Height++;
                } else {
                    height++;
                }
            }
        }

        return height>lengthOf1Height;
    }

    static void moveAndRotate() {
        int startCol = 0;
        for (int j = 99;j>=0;j--) {
            if (map[1][j] != 0) {
                startCol = j;
                break;
            }
        }

        for (int j = startCol;j>=0;j--) {
            for (int i = 0;i<=99;i++) {
                if (map[i][j]==-1) break;
                map[startCol-j+1][j+i+1] = map[i][j];
            }
        }
    }
}
