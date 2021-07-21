package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DiceRolling {
    public static void main(String args[]) throws IOException {
        int dice[] = {0, 0, 0, 0, 0, 0, 0};
        int dr[] = {0, 0, 0, -1, 1};
        int dc[] = {0, 1, -1, 0, 0};
        int N, M, x, y, K;

        int map[][] = new int[30][30];
        int commands[] = new int[1010];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        str = br.readLine();
        st = new StringTokenizer(str, " ");
        for (int i = 0; i < K; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        int r = x;
        int c = y;

        for (int i = 0; i < K; i++) {
            int dir = commands[i];
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                continue;
            }
            dice = rolling(dice,dir);
            r = nr;
            c = nc;
            if (map[r][c] == 0) {
                map[r][c] = dice[6];
            } else {
                dice[6] = map[r][c];
                map[r][c] = 0;
            }
            System.out.println(dice[1]);
        }
    }

    static int[] rolling(int[] dice,int dir) {
        int[] temp = new int[7];
        if (dir==1) {
            temp[1] = dice[4];
            temp[2] = dice[2];
            temp[3] = dice[1];
            temp[4] = dice[6];
            temp[5] = dice[5];
            temp[6] = dice[3];
        } else if(dir==2) {
            temp[1] = dice[3];
            temp[2] = dice[2];
            temp[3] = dice[6];
            temp[4] = dice[1];
            temp[5] = dice[5];
            temp[6] = dice[4];
        } else if(dir==3) {
            temp[1] = dice[5];
            temp[2] = dice[1];
            temp[3] = dice[3];
            temp[4] = dice[4];
            temp[5] = dice[6];
            temp[6] = dice[2];
        } else if(dir==4) {
            temp[1] = dice[2];
            temp[2] = dice[6];
            temp[3] = dice[3];
            temp[4] = dice[4];
            temp[5] = dice[1];
            temp[6] = dice[5];
        }
        return temp;
    }
}

