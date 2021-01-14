package solutions.implementaition;

public class GroundPicking {
  static int solution(int[][] land) {
    int answer = 0;

    int dp[][] = new int[land.length][land[0].length];

    for (int i =0;i<4;i++) {
      dp[0][i] = land[0][i];
    }

    for (int i = 1;i< land.length;i++) {
      dp[i][0] = Math.max(dp[i-1][1],Math.max(dp[i-1][2],dp[i-1][3])) + land[i][0];
      dp[i][1] = Math.max(dp[i-1][0],Math.max(dp[i-1][2],dp[i-1][3])) + land[i][1];
      dp[i][2] = Math.max(dp[i-1][1],Math.max(dp[i-1][0],dp[i-1][3])) + land[i][2];
      dp[i][3] = Math.max(dp[i-1][1],Math.max(dp[i-1][2],dp[i-1][0])) + land[i][3];
    }

    int len = land.length;

    answer = Math.max(Math.max(dp[len-1][0],dp[len-1][1]),Math.max(dp[len-1][2],dp[len-1][3]));

    return answer;
  }

  public static void main(String args[]) {
    System.out.println(solution(new int[][]{{1,2,3,5},{5,6,7,8},{4,3,2,1}}));
  }
}
