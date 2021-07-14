package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MagicianSharkAndRainCloud {

  static int N;
  static int M;
  static int[][] map;
  static int[] dr = {-1,0,1,0};
  static int[] dc = {0,1,0,-1};
  static int[] directions;
  static int[] speeds;
  static ArrayList<Pair> clouds;

  static class Pair {
    int r,c;
    Pair(int x,int y) {
      r = x;
      c = y;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str, " ");

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N+1][N+1];
    directions = new int[M];
    speeds = new int[M];

    for (int i = 1;i<=N;i++) {
      str = br.readLine();
      st = new StringTokenizer(str, " ");
      for (int j = 1;j<=N;j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0;i<M;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      directions[i] = Integer.parseInt(st.nextToken());
      speeds[i] = Integer.parseInt(st.nextToken());
    }

    for (int i = 0;i<M;i++) {
      move(i);
    }

  }

  static void move(int inx) {
    moveCloud(directions[inx], speeds[inx]);
    rain();
  }
}
