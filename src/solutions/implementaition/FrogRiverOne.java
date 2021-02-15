package solutions.implementaition;

public class FrogRiverOne {
  public int solution(int X, int[] A) {
    int N = A.length;
    int coveredCnt  = 0;
    boolean checked[] = new boolean[100010];
    int answer = -1;
    for (int i = 0;i<N;i++) {
      int number = A[i];
      if (!checked[number]) {
        checked[number] = true;
        coveredCnt++;
      }
      if (coveredCnt == X) {
        answer = i;
        break;
      }
    }
    return answer;
  }
}
