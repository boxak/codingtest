package solutions.implementaition;

public class PermMissingElem {
  public int solution(int[] A) {
    int N = A.length;
    boolean checked[] = new boolean[100010];

    for (int i = 0;i<N;i++) {
      int number = A[i];
      checked[number] = true;
    }

    int answer = -1;
    for (int i = 1;i<=N+1;i++) {
      if (!checked[i]) {
        answer = i;
        break;
      }
    }
    return answer;
  }
}
