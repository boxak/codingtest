package solutions.implementaition;

public class IntegerTriangle {
    public int solution(int[][] triangle) {
        int answer = 0;

        int h = triangle.length;

        int[][] dp = new int[h][];

        for (int i = 0;i<h;i++) {
            dp[i] = new int[i+1];
        }

        dp[0][0] = triangle[0][0];

        for (int i = 1;i<h;i++) {
            dp[i][0] = dp[i-1][0] + triangle[i][0];
            dp[i][i] = dp[i-1][i-1] + triangle[i][i];
        }

        for (int i = 2;i<h;i++) {
            for (int j = 1;j<=i-1;j++) {
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
            }
        }

        for (int i = 0;i<h;i++) {
            answer = Math.max(answer, dp[h-1][i]);
        }

        return answer;
    }
}
