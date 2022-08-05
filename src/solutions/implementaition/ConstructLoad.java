package solutions.implementaition;

public class ConstructLoad {
    int minValue = Integer.MAX_VALUE;
    int[] dr = {-1,0,1,0};
    int[] dc = {0,1,0,-1};
    int N;
    int[][][] dp;

    public int solution(int[][] board) {
        int answer = 0;

        N = board.length;
        dp = new int[N][N][4];

        for (int i = 0;i<N;i++) {
            for (int j = 0;j<N;j++) {
                for (int k = 0;k<4;k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        dp[0][0][1] = 0;
        dp[0][0][2] = 0;

        if (board[0][1] == 0) {
            dfs(board, 0, 0, 1, 0, 0);
        }

        if (board[1][0] == 0) {
            dfs(board, 0,0, 2, 0,0);
        }

        answer = minValue;

        return answer;
    }

    void dfs(int[][] board, int r, int c, int d, int straight, int corner) {
        dp[r][c][d] = 100*straight+500*corner;
        // System.out.println(r+" "+c+" "+d+" "+dp[r][c]);
        if (r == N-1 && c == N-1) {
            minValue = Math.min(minValue, 100*straight+500*corner);
            return;
        }

        for (int dir = 0;dir<4;dir++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if (nr < 0 || nr >= N || nc < 0 || nc>= N) continue;
            if (board[nr][nc] == 0) {
                if (dir == d) {
                    if ((straight+1)*100+corner*500<dp[nr][nc][dir]) {
                        dfs(board, nr, nc, dir, straight+1, corner);
                    }
                } else {
                    if ((straight+1)*100+(corner+1)*500<dp[nr][nc][dir]) {
                        dfs(board, nr, nc, dir, straight+1, corner+1);
                    }
                }
            }
        }

    }
}
