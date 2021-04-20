package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AdultShark {

  static class Pair {
    int r;
    int c;
    Pair(int x,int y) {
      r = x;
      c = y;
    }
  }

  static class Shark {
    int inx;
    int r;
    int c;
    int dir;
    ArrayList<Pair> list;
    int[][] priority;
    Shark(int x,int y,int z,int xx) {
      inx = x;
      r = y;
      c = z;
      dir = xx;
      list = new ArrayList<>();
      list.add(new Pair(r,c));
    }
  }

  static int[][] prints;
  static int[][] map;
  static int[] dr = {0,-1,1,0,0};
  static int[] dc = {0,0,0,-1,1};
  static int N,M,k;
  static ArrayList<Shark> sharks;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    prints = new int[N+1][N+1];
    map = new int[N+1][N+1];
    sharks = new ArrayList<>();

    for (int i = 1;i<=N;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      for (int j = 1;j<=N;j++) {
        prints[i][j] = Integer.parseInt(st.nextToken());
        map[i][j] = prints[i][j];
        if (prints[i][j] > 0) {
          sharks.add(new Shark(prints[i][j],i,j,0));
        }
      }
    }
    str = br.readLine();
    st = new StringTokenizer(str," ");
    for (int i = 0;i<M;i++) {
      Shark shark = sharks.get(i);
      int dir = Integer.parseInt(st.nextToken());
      sharks.set(i,new Shark(shark.inx,shark.r,shark.c,dir));
    }

    for (int i = 0;i<M;i++) {
      Shark shark = sharks.get(i);
      int[][] priority = new int[4][4];
      for (int j = 0;j<4;j++) {
        str = br.readLine();
        st = new StringTokenizer(str," ");
        for (int k = 0;k<4;k++) {
          priority[j][k] = Integer.parseInt(st.nextToken());
        }
      }
      shark.priority = priority;
    }

    int answer = -1;

    for (int tt = 0;tt<1000;tt++) {
      for (int i = 0;i<sharks.size();i++) {
        Shark shark = sharks.get(i);
        int r = shark.r;
        int c = shark.c;
        ArrayList<Pair> footprint = shark.list;
        int[][] priority = shark.priority;
        boolean flag = false;
        for (int d = 0;d<4;d++) {
          int dir = priority[shark.dir][d];
          int nr = r+dr[dir];
          int nc = c+dc[dir];
          if (nr<1 || nr>N || nc<1 || nc>N) continue;
          if (prints[nr][nc]==0) {
            flag = true;
            prints[nr][nc] = shark.inx;
            map[r][c] = 0;
            map[nr][nc] = shark.inx;
            footprint.add(new Pair(nr,nc));
          }
        }
        if (!flag) {
          for (int d = 0;d<4;d++) {
            int dir = priority[shark.dir][d];
            int nr = r+dr[dir];
            int nc = c+dc[dir];
            if (nr<1 || nr>N || nc<1 || nc>N) continue;
            if (prints[nr][nc]!=0 && map[nr][nc] > shark.inx) {
              prints[nr][nc] = shark.inx;
              map[r][c] = 0;

            }
          }
        }
      }
    }

  }
}
