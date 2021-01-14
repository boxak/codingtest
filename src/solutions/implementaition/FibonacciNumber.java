package solutions.implementaition;

public class FibonacciNumber {
  public int solution(int n) {
    int answer = 0;

    int dp[] = new int[100010];

    dp[0] = 0;
    dp[1] = 1;

    for (int i = 2;i<=n;i++) {
      dp[i] = (dp[i-1] + dp[i-2])%1234567;
    }

    answer = dp[n];

    return answer;
  }
}
