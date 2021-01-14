package solutions.implementaition;

public class MatrixProduct {
  public int[][] solution(int[][] arr1, int[][] arr2) {
    int[][] answer = {};

    int r1 = arr1.length;
    int c1 = arr1[0].length;

    int r2 = arr2.length;
    int c2 = arr2[0].length;

    answer = new int[r1][c2];

    for (int i = 0;i<r1;i++) {
      for (int j = 0;j<c2;j++) {
        int sum = 0;
        for (int f = 0;f<c1;f++) {
          sum+=arr1[i][f]*arr2[f][j];
        }
        answer[i][j] = sum;
      }
    }

    return answer;
  }
}
