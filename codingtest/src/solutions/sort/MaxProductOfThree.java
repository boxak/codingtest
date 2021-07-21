package solutions.sort;

import java.util.Arrays;

public class MaxProductOfThree {
  public int solution(int[] A) {
    Arrays.sort(A);
    int N = A.length;

    int firstProd = A[0]*A[1]*A[N-1];
    int secondProd = A[N-3]*A[N-2]*A[N-1];

    return Math.max(firstProd, secondProd);
  }
}
