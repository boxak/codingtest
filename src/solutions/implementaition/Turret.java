 package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Turret {
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    int answers[] = new int[T];

    for (int test = 0;test<T;test++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int r1 = Integer.parseInt(st.nextToken());

      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      int r2 = Integer.parseInt(st.nextToken());

      int square = (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1);

      double d = Math.sqrt(square);
      int big = Math.max(r1,r2);
      int small = Math.min(r1,r2);

      if (d>r1+r2 || d<big-small) answers[test] = 0;
      if (d==r1+r2 || d==big-small) answers[test] = 1;
      if (d<r1+r2 && d>big-small) answers[test] = 2;

    }

    for (int answer : answers) {
      System.out.println(answer);
    }

    System.out.println("zzzzz");

  }
}
