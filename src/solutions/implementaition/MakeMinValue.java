package solutions.implementaition;

import java.util.Arrays;

public class MakeMinValue {
  public int solution(int[] A, int[] B) {
    int answer = 0;

    Arrays.sort(A);
    Arrays.sort(B);

    int len = A.length;

    for (int i = 0;i<len;i++) {
      int num1 = A[i];
      int num2 = B[len-1-i];
      answer+=num1*num2;
    }

    return answer;
  }
}
