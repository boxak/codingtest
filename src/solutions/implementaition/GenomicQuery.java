package solutions.implementaition;

public class GenomicQuery {
  public static int[] solution(String S, int[] P, int[] Q) {
    int N = S.length();
    int M = P.length;

    int position[] = new int[4];
    position[0] = -1; position[1] = -1; position[2] = -1; position[3] = -1;
    int dp[][] = new int[100010][4];

    for (int i = 0;i<N;i++) {
      if (S.charAt(i)=='A') {
        position[0] = i;
      }
      if (S.charAt(i)=='C') {
        position[1] = i;
      }
      if (S.charAt(i)=='G') {
        position[2] = i;
      }
      if (S.charAt(i)=='T') {
        position[3] = i;
      }
      dp[i][0] = position[0];
      dp[i][1] = position[1];
      dp[i][2] = position[2];
      dp[i][3] = position[3];
    }

    int[] answer = new int[M];

    for (int i = 0;i<M;i++) {
      int inx1 = P[i];
      int inx2 = Q[i];
      for (int j = 0;j<4;j++) {
        if (dp[inx2][j]>=inx1) {
          answer[i] = j+1;
          break;
        }
      }
    }

    return answer;
  }

  public static void main(String args[]) {
    int[] answer = solution("CAGCCTA", new int[]{2,5,0}, new int[]{4,5,6});
    for (int num : answer) {
      System.out.printf("%d ",num);
    }
  }
}
