package solutions.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ExitFromFire {

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
    int R = Integer.parseInt(st.nextToken());
    int C = Integer.parseInt(st.nextToken());

    char[][] map = new char[R+1][C+1];
    int[] dr = {-1,0,1,0};
    int[] dc = {0,1,0,-1};
    int sr = -1;
    int sc = -1;
    Queue<Pair> fireQue = new LinkedList<>();

    for (int i = 1;i<=R;i++) {
      str = br.readLine();
      for (int j = 1;j<=C;j++) {
        char c = str.charAt(j-1);
        if (c=='J') {
          sr = i;
          sc = j;
          c = '.';
        }
        if (c=='F') {
          fireQue.add(new Pair(i,j));
        }
        map[i][j] = c;
      }
    }


    boolean[][] visited = new boolean[R+1][C+1];
    Queue<Pair> que = new LinkedList<>();
    visited[sr][sc] = true;
    que.add(new Pair(sr,sc));
    boolean finish = false;
    int time = 0;

    while(!que.isEmpty()) {
      time++;
      int fireCnt = fireQue.size();
      for (int cnt = 0;cnt<fireCnt;cnt++) {
        int r = fireQue.peek().r;
        int c = fireQue.peek().c;
        fireQue.poll();
        for (int i = 0;i<4;i++) {
          int nr = r+dr[i];
          int nc = c+dc[i];
          if (nr<1 || nr>R || nc<1 || nc>C) continue;
          if (map[nr][nc]=='.') {
            map[nr][nc] = 'F';
            fireQue.add(new Pair(nr,nc));
          }
        }
      }
      int queSize = que.size();
      for (int cnt = 0;cnt<queSize;cnt++) {
        int r = que.peek().r;
        int c = que.peek().c;
        que.poll();
        for (int i = 0;i<4;i++) {
          int nr = r+dr[i];
          int nc = c+dc[i];
          if (nr<1 || nr>R || nc<1 || nc>C) {
            finish = true;
            break;
          }
          if (map[nr][nc]=='.' && !visited[nr][nc]) {
            visited[nr][nc] = true;
            que.add(new Pair(nr,nc));
          }
        }
        if (finish) break;
      }
      if (finish) break;
    }
    if (!finish) System.out.println("IMPOSSIBLE");
    else System.out.println(time);
  }
}
