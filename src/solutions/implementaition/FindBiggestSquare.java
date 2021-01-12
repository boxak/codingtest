package solutions.implementaition;

public class FindBiggestSquare {
    public static int solution(int[][] board) {
        int answer = 0;
        int len = 0;

        int dp[][] = new int[1010][1010];
        int rowSize = board.length;
        int colSize = board[0].length;

        for (int i = 0; i < rowSize; i++) {
            dp[i][0] = board[i][0];
        }

        for (int j = 0; j < colSize; j++) {
            dp[0][j] = board[0][j];
        }

        for (int i = 1; i < rowSize; i++) {
            for (int j = 1; j < colSize; j++) {
                if (board[i][j] != 0) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (len < dp[i][j]) len = dp[i][j];
            }
        }
        answer = len * len;
        return answer;
    }

    public static void main(String[] args) {
        solution(new int[][]{{0, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {0, 0, 1, 0}});
    }
}
