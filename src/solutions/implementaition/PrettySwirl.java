package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrettySwirl {
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");

    int r1 = Integer.parseInt(st.nextToken());
    int c1 = Integer.parseInt(st.nextToken());
    int r2 = Integer.parseInt(st.nextToken());
    int c2 = Integer.parseInt(st.nextToken());

    int[][] map = new int[60][10];
    int r = 5000;
    int c = 5000;
    int num = 1;
    int dir = 1;
    int[] dr = {-1,0,1,0};
    int[] dc = {0,1,0,-1};
    int dirChangeCount = 0;
    int goCount = 0;
    int goCountLimit = 1;
    int limit = 10001*10001;

    while(num<=limit) {
      if (r1+5000<=r && r<=r2+5000 && c1+5000<=c && c<=c2+5000) {
        int temp_r = r - (r1+5000);
        int temp_c = c - (c1+5000);
        map[temp_r][temp_c] = num;
      }
      num++;
      goCount++;
      r = r+dr[dir];
      c = c+dc[dir];
      if (goCount==goCountLimit) {
        dir--;
        goCount = 0;
        if (dir<0) dir = 3;
        dirChangeCount++;
        if (dirChangeCount==2) {
          goCountLimit++;
          dirChangeCount = 0;
        }
      }
    }

    int max = Integer.MIN_VALUE;

    for (int i = 0;i<=r2-r1;i++) {
      for (int j = 0;j<=c2-c1;j++) {
        String numStr = String.valueOf(map[i][j]);
        if (numStr.length()>max) max = numStr.length();
      }
    }

    for (int i = 0;i<=r2-r1;i++) {
      for (int j = 0;j<=c2-c1;j++) {
        int len = String.valueOf(map[i][j]).length();
        for (int cnt = 0;cnt<max-len;cnt++) System.out.print(" ");
        System.out.print(map[i][j]+" ");
      }
      System.out.println();
    }
  }
}
