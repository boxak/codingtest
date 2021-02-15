package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Lab2 {

  static int N,M,virusCnt;
  static int[][] map;
  static int[] dr = {-1,0,1,0};
  static int[] dc = {0,1,0,-1};
  static int Answer = Integer.MAX_VALUE;
  static boolean findCase = false;

  static class Pair {
    int r;
    int c;
    Pair(int x, int y) {
      r = x;
      c = y;
    }
  }

  static Pair[] virusArr = new Pair[10];
  static int[] choice = new int[10];

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[60][60];

    for (int i = 0;i<N;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      for (int j = 0;j<N;j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] == 2) {
          virusArr[virusCnt++] = new Pair(i,j);
        }
      }
    }

    dfs(0,0);
    if (findCase) {
      System.out.println(Answer);
    } else {
      System.out.println(-1);
    }


  }

  static void dfs(int inx,int cnt) {
    if (inx == virusCnt) {
      if (cnt == M) {
        bfs();
      }
      return;
    }
    if (cnt>M) return;
    choice[inx] = 0;
    dfs(inx+1, cnt);
    choice[inx] = 1;
    dfs(inx+1, cnt+1);
  }

  static void bfs() {
    boolean[][] visited = new boolean[60][60];
    Queue<Pair> que = new LinkedList<>();
    for (int i = 0;i<virusCnt;i++) {
      if (choice[i] == 1) {
        int r = virusArr[i].r;
        int c = virusArr[i].c;
        que.add(new Pair(r,c));
        visited[r][c] = true;
      }
    }

    int time = 0;

    while(true) {
      int queSize = que.size();
      for (int count = 0;count<queSize;count++) {
        int r = que.peek().r;
        int c = que.peek().c;
        que.poll();
        for (int i = 0;i<4;i++) {
          int nr = r+dr[i];
          int nc = c+dc[i];
          if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
          if (map[nr][nc] != 1 && !visited[nr][nc]) {
            visited[nr][nc] = true;
            que.add(new Pair(nr, nc));
          }
        }
      }
      if (que.isEmpty()) break;
      time++;
    }

    boolean flag = true;

    for (int i = 0;i<N;i++) {
      for (int j =0;j<N;j++) {
        if (map[i][j]!=1 && !visited[i][j]) {
          flag = false;
        }
      }
    }
    if (flag) {
      if (time<Answer) Answer = time;
      findCase = true;
    }
  }

}
