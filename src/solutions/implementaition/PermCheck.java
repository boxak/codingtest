package solutions.implementaition;

public class PermCheck {
  public int solution(int[] A) {
    int N = A.length;
    boolean checked[] = new boolean[N+1];

    for (int i = 0;i<N;i++) {
      int number = A[i];
      if (number<=N) {
        checked[number] = true;
      }
    }

    boolean isPermutation = true;
    for (int i = 1;i<=N;i++) {
      if (!checked[i]) {
        isPermutation = false;
        break;
      }
    }
    return isPermutation ? 1 : 0;
  }
}
