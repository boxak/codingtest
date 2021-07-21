package solutions.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato {

  static class Pair {
    int r;
    int c;
    Pair(int x,int y) {
      r = x;
      c = y;
    }
  }

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");
    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    int map[][] = new int[N+1][M+1];
    int dr[] = {-1,0,1,0};
    int dc[] = {0,1,0,-1};
    Queue<Pair> que = new LinkedList<>();

    for (int i = 1;i<=N;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      for (int j = 1;j<=M;j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] == 1) {
          que.add(new Pair(i,j));
        }
      }
    }

    int answer = 0;

    while(true) {
      int queSize = que.size();
      for (int ff=0;ff<queSize;ff++) {
        int r = que.peek().r;
        int c = que.peek().c;
        que.poll();
        for (int i = 0;i<4;i++) {
          int nr = r+dr[i];
          int nc = c+dc[i];
          if (nr<1 || nr>N || nc<1 || nc>M) continue;
          if (map[nr][nc]==0) {
            map[nr][nc] = 1;
            que.add(new Pair(nr,nc));
          }
        }
      }
      if (que.isEmpty()) break;
      answer++;
    }

    for (int i = 1;i<=N;i++) {
      for (int j=1;j<=M;j++) {
        if (map[i][j] == 0) {
          System.out.println(-1);
          return;
        }
      }
    }

    System.out.println(answer);

  }
}
