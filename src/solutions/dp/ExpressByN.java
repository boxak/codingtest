package solutions.dp;

import java.util.ArrayList;

public class ExpressByN {
  public static int solution(int N, int number) {
    int answer = -1;

    int[] dp = new int[100000];

    for (int i = 1; i < 100000; i++) {
      dp[i] = Integer.MAX_VALUE;
    }

    dp[N] = 1;
    dp[Integer.parseInt(N + "" + N)] = 2;
    dp[Integer.parseInt(N + "" + N + "" + N)] = 3;
    dp[Integer.parseInt(N + "" + N + "" + N + "" + N)] = 4;
    dp[Integer.parseInt(N + "" + N + "" + N + "" + N + "" + N)] = 5;
    ArrayList<Integer> list = new ArrayList<>();
    list.add(N);
    list.add(Integer.parseInt(N + "" + N));
    list.add(Integer.parseInt(N + "" + N + "" + N));
    list.add(Integer.parseInt(N + "" + N + "" + N + "" + N));
    list.add(Integer.parseInt(N + "" + N + "" + N + "" + N + "" + N));

    for (int cnt = 0;cnt<3;cnt++) {
      int listSize = list.size();
      for (int i = 0; i < listSize; i++) {
        int num1 = list.get(i);
        for (int j = 0; j < listSize; j++) {
          int num2 = list.get(j);
          int[] arr = new int[]{num1 + num2, num1 - num2, num1 * num2, num1 / num2};
          for (int k = 0; k < 4; k++) {
            if (arr[k]<=0 || arr[k]>=100000) continue;
            if (dp[arr[k]] > dp[num1] + dp[num2]) {
              dp[arr[k]] = dp[num1] + dp[num2];
              list.add(arr[k]);
            }
          }
        }
      }
    }

    if (dp[number] > 8) answer = -1;
    else answer = dp[number];

    return answer;
  }

  public static void main(String args[]) {
    System.out.println(solution(9,10001));
  }

}
