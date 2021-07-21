package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MagicianSharkAndRainCloud {

  static int N;
  static int M;
  static int[][] map;
  static int[] dr = {0,0,-1,-1,-1,0,1,1,1};
  static int[] dc = {0,-1,-1,0,1,1,1,0,-1};
  static int[] directions;
  static int[] speeds;
  static ArrayList<Pair> clouds;
  static ArrayList<Pair> newClouds;

  static class Pair {
    int r,c;
    Pair(int x,int y) {
      r = x;
      c = y;
    }

    @Override
    public boolean equals(Object pair) {
      return this.r==((Pair)pair).r && this.c==((Pair)pair).c;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str, " ");

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][N];
    directions = new int[M];
    speeds = new int[M];
    clouds = new ArrayList<>();
    newClouds = new ArrayList<>();

    for (int i = 0;i<N;i++) {
      str = br.readLine();
      st = new StringTokenizer(str, " ");
      for (int j = 0;j<N;j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0;i<M;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      directions[i] = Integer.parseInt(st.nextToken());
      speeds[i] = Integer.parseInt(st.nextToken());
    }

    clouds.add(new Pair(N-1,0));
    clouds.add(new Pair(N-1,1));
    clouds.add(new Pair(N-2,0));
    clouds.add(new Pair(N-2,1));

    for (int i = 0;i<M;i++) {
      move(i);
    }

    int waterSum = 0;

    for (int i = 0;i<N;i++) {
      for (int j = 0;j<N;j++) {
        waterSum+=map[i][j];
      }
    }

    System.out.println(waterSum);

  }

  static void move(int inx) {
    moveCloud(directions[inx], speeds[inx]);
    rain();
    waterCopy();
    makeCloud();
    removeCloud();
  }

  static void moveCloud(int dir, int speed) {
    for (int i = 0;i< clouds.size();i++) {
      int r = clouds.get(i).r;
      int c = clouds.get(i).c;
      int nr = r + dr[dir]*speed;
      int nc = c + dc[dir]*speed;

      Pair tempPair = resetPosition(nr,nc);

      nr = tempPair.r;
      nc = tempPair.c;

      clouds.set(i,new Pair(nr,nc));
    }
  }

  static Pair resetPosition(int r,int c) {

    int rr = r;
    int cc = c;

    if (r>=0) {
      rr = r%N;
    } else {
      int rrr = -rr;
      rrr = rrr%N;
      if (rrr!=0) {
        rrr = N - rrr;
      }
      rr = rrr;
    }

    if (c>=0) {
      cc = c%N;
    } else {
      int ccc = -cc;
      ccc = ccc%N;
      if (ccc!=0) {
        ccc = N - ccc;
      }
      cc = ccc;
    }

    return new Pair(rr,cc);
  }

  static void rain() {
    for (Pair cloud : clouds) {
      int r = cloud.r;
      int c = cloud.c;

      map[r][c]++;
    }
  }

  static void removeCloud() {
    clouds.clear();
    for (Pair newCloud : newClouds) {
      clouds.add(newCloud);
    }
    newClouds.clear();
  }

  static void waterCopy() {
    int[][] tempMap = new int[N][N];

    for (int i = 0;i<N;i++) {
      for (int j = 0;j<N;j++) {
        tempMap[i][j] = map[i][j];
      }
    }

    int[] dr2 = {-1,-1,1,1};
    int[] dc2 = {-1,1,1,-1};

    for (int i = 0;i< clouds.size();i++) {
      int r = clouds.get(i).r;
      int c = clouds.get(i).c;

      int cnt = 0;

      for (int d = 0;d<4;d++) {
        int nr = r+dr2[d];
        int nc = c+dc2[d];
        if (nr<0 || nr>=N || nc<0 || nc>=N) continue;

        if (tempMap[nr][nc]>0) cnt++;
      }

      map[r][c]+=cnt;
    }
  }

  static void makeCloud() {
    for (int i = 0;i<N;i++) {
      for (int j = 0;j<N;j++) {
        Pair newCloud = new Pair(i,j);
        if (!clouds.contains(newCloud) && map[i][j]>=2) {
          newClouds.add(newCloud);
          map[i][j]-=2;
        }
      }
    }
  }
}
