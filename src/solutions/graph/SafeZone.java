package solutions.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SafeZone {

  static class Pair {
    int r,c;
    Pair(int r,int c) {
      this.r = r;
      this.c = c;
    }
  }

  static final int[] dr = {-1,0,1,0};
  static final int[] dc = {0,1,0,-1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    int[][] map = new int[N+1][N+1];

    for (int i = 1;i<=N;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");
      for (int j = 1;j<=N;j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int answer = -1;

    int maxHeight = findMaxHeight(N,map);

    for (int height = 0;height<=maxHeight;height++) {
      int safeZoneCnt = getSafeZoneCnt(N,height,map);
      if (safeZoneCnt>answer) {
        answer = safeZoneCnt;
      }
    }

    System.out.println(answer);

  }

  static int findMaxHeight(int N, int[][] map) {
    int maxHeight = -1;
    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        if (map[i][j]>maxHeight) maxHeight = map[i][j];
      }
    }
    return maxHeight;
  }

  static int getSafeZoneCnt(int N,int height,int[][] map) {

    boolean[][] visited = new boolean[N+1][N+1];
    int safeZoneCnt = 0;

    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        if (map[i][j]>height && !visited[i][j]) {
          safeZoneCnt++;
          bfs(N,height,i,j,map,visited);
        }
      }
    }
    return safeZoneCnt;
  }

  static void bfs(int N,int height, int sr,int sc,int[][] map,boolean[][] visited) {
    Queue<Pair> que = new LinkedList<>();

    que.add(new Pair(sr,sc));
    visited[sr][sc] = true;

    while(!que.isEmpty()) {
      int r = que.peek().r;
      int c = que.peek().c;
      que.poll();
      for (int d=0;d<4;d++) {
        int nr = r+dr[d];
        int nc = c+dc[d];
        if (nr<1 || nr>N || nc<1 || nc>N) continue;
        if (map[nr][nc]>height && !visited[nr][nc]) {
          visited[nr][nc] = true;
          que.add(new Pair(nr,nc));
        }
      }
    }
  }
}
