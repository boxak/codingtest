package solutions.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StartAndLink {

    static int N;
    static int[] teams;
    static int[][] stats;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        teams = new int[N];
        stats = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            stats[i] = new int[N];
            // i행의 j열 원소 = i,j가 같은 팀일 때 팀에 더해지는 능력치
            for (int j = 0; j < N; j++) {
                stats[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, N/2, 0, 0);

        System.out.println(answer);
    }

    static void dfs(int x, int limit, int teamCnt1, int teamCnt2) {
        if (x == N) {
            int diff = calculateDiff();

            answer = Math.min(answer, diff);
            return;
        }

        if (teamCnt1 < limit) {
            teams[x] = 1;
            dfs(x+1, limit,teamCnt1 + 1, teamCnt2);
        }

        if (teamCnt2 < limit) {
            teams[x] = 2;
            dfs(x+1, limit, teamCnt1, teamCnt2 + 1);
        }

    }

    static int calculateDiff() {
        int sum1 = 0;
        int sum2 = 0;

        List<Integer> team1 = new ArrayList<>();
        List<Integer> team2 = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (teams[i] == 1) {
                team1.add(i);
            } else {
                team2.add(i);
            }
        }

        for (int i = 0; i < N / 2; i++) {
            for (int j = i + 1; j < N / 2; j++) {
                int index1 = team1.get(i);
                int index2 = team1.get(j);
                sum1 += stats[index1][index2] + stats[index2][index1];
            }
        }

        for (int i = 0; i < N / 2; i++) {
            for (int j = i + 1; j < N / 2; j++) {
                int index1 = team2.get(i);
                int index2 = team2.get(j);
                sum2 += stats[index1][index2] + stats[index2][index1];
            }
        }

        return Math.abs(sum1 - sum2);
    }
}
