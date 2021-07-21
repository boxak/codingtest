package solutions.implementaition;

public class MissingInteger {
  public int solution(int[] A) {
    boolean checked[] = new boolean[1000010];
    for (int i = 0;i<A.length;i++) {
      if (A[i]>0) {
        checked[A[i]] = true;
      }
    }

    int answer = -1;
    for (int i = 1;;i++) {
      if (!checked[i]) {
        answer = i;
        break;
      }
    }
    return answer;
  }
}
