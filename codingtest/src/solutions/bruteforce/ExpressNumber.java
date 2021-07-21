package solutions.bruteforce;

public class ExpressNumber {
  public int solution(int n) {
    int answer = 0;

    for (int i = 1;i<=n;i++) {
      int sum = 0;
      for (int j=i;j<=n;j++) {
        if (sum+j>n) break;
        sum+=j;
      }
      if (sum == n) answer++;
    }

    return answer;
  }
}
