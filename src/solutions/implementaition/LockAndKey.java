package solutions.implementaition;

import java.util.ArrayList;

public class LockAndKey {

  public boolean solution(int[][] key, int[][] lock) {
    boolean answer = false;

    int n = key.length;
    int m = lock.length;

    for (int rot = 0;rot<=3;rot++) {
      if (rot > 0) {
        rotate(key);
      }

      for (int sr = -n+1;sr<m;sr++) {
        for (int sc = -n+1;sc<m;sc++) {

          boolean[][] check = new boolean[m][m];

          for (int i = 0;i<m;i++) {
            for (int j = 0;j<m;j++) {
              if (lock[i][j] == 1) {
                check[i][j] = true;
              }
            }
          }

          for (int i = 0;i<n;i++) {
            for (int j = 0;j<n;j++) {
              int row = sr + i;
              int col = sc + j;
              if (row < 0 || row >= m || col < 0 || col >= m) continue;

              if (key[i][j] == 1 && lock[row][col] == 0) {
                check[row][col] = true;
              }

              if (key[i][j] == 1 && lock[row][col] == 1) {
                check[row][col] = false;
              }

            }
          }

          boolean flag = true;

          for (int i = 0;i<m;i++) {
            for (int j = 0;j<m;j++) {
              if (!check[i][j]) {
                flag = false;
                break;
              }
            }
            if (!flag) break;
          }

          if (flag) {
            answer = true;
            break;
          }

        }
        if (answer) break;
      }

    }

    return answer;
  }

  void rotate(int[][] key) {
    int n = key.length;
    int[][] temp = new int[n][n];

    for (int i = 0;i<n;i++) {
      for (int j = 0;j<n;j++) {
        temp[j][n-1-i] = key[i][j];
      }
    }

    for (int i = 0;i<n;i++) {
      for (int j = 0;j<n;j++) {
        key[i][j] = temp[i][j];
      }
    }

  }
}
