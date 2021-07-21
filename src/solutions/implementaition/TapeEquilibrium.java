package solutions.implementaition;

public class TapeEquilibrium {
  public int solution(int[] A) {

    int N = A.length;
    int first = A[0];
    int second = 0;

    for (int i = 1;i<N;i++) {
      second+=A[i];
    }

    int answer = Math.abs(first - second);
    for (int i = 1;i<N-1;i++) {
      first+=A[i];
      second-=A[i];
      if (Math.abs(first - second)<answer) {
        answer = Math.abs(first - second);
      }
    }

    return answer;
  }
}
