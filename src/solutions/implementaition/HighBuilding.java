package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HighBuilding {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[] buildings = new int[N];
    int answer = Integer.MIN_VALUE;

    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");

    for (int i = 0;i<N;i++) {
      int height = Integer.parseInt(st.nextToken());
      buildings[i] = height;
    }

    for (int i = 0;i<N;i++) {
      int cnt = 0;
      for (int j = 0;j<N;j++) {
        if (i==j) continue;
        int x1 = i>j ? j : i;
        int x2 = i<j ? j : i;
        int h1 = buildings[x1];
        int h2 = buildings[x2];
        double m = ((double)(h2-h1))/(x2-x1);
        boolean flag = true;
        for (int k = x1+1;k<x2;k++) {
          double y_OnLine = m*(k-x1)+h1;
          if (y_OnLine <= buildings[k]) {
            flag = false;
            break;
          }
        }
        if (flag) {
          cnt++;
        }
      }
      if (cnt>answer) answer = cnt;
    }
    System.out.println(answer);
  }

}
