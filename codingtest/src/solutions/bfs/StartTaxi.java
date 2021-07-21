package solutions.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class StartTaxi {

  static class Guest {
    int sr;
    int sc;
    int er;
    int ec;
    Guest (int x,int y,int w,int z) {
      sr = x;
      sc = y;
      er = w;
      ec = z;
    }
  }

  static class Pair implements Comparable<Pair> {
    int r;
    int c;
    int d;
    Pair(int x,int y,int z) {
      r = x;
      c = y;
      d = z;
    }

    public int compareTo(Pair pair) {
      if (this.d == pair.d) {
        if (this.r == pair.r) {
          return this.c - pair.c;
        } else
          return this.r - pair.r;
      } else return this.d - pair.d;
    }
  }

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int L = Integer.parseInt(st.nextToken());

    int[][] map = new int[N+1][N+1];
    for (int i = 1;i<=N;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      for (int j = 1;j<=N;j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    str = br.readLine();
    st = new StringTokenizer(str," ");

    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());

    ArrayList<Guest> list = new ArrayList<Guest>();

    for (int i = 0;i<M;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int f = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());

      list.add(new Guest(a,b,f,d));
      map[a][b] = i+2;
    }

    int cntFinish = 0;
    int[] dr = {-1,0,1,0};
    int[] dc = {0,1,0,-1};

    while(cntFinish<M) {
      //현재 위치에서 최단 거리 손님 탐색
      Queue<Pair> que = new LinkedList<>();
      boolean[][] visited = new boolean[N+1][N+1];

      que.add(new Pair(r,c,0));
      visited[r][c] = true;
      ArrayList<Pair> guestPos = new ArrayList<>();

      while(!que.isEmpty()) {
        int cr = que.peek().r;
        int cc = que.peek().c;
        int cd = que.peek().d;
        que.poll();
        if (map[cr][cc]>=2) {
          guestPos.add(new Pair(cr,cc,cd));
        }
        for (int i = 0;i<4;i++) {
          int nr = cr+dr[i];
          int nc = cc+dc[i];
          if (nr<1 || nr>N || nc<1 || nc>N) continue;
          if (map[nr][nc]!=1 && !visited[nr][nc]) {
            visited[nr][nc] = true;
            que.add(new Pair(nr,nc,cd+1));
          }
        }
      }
      if (guestPos.isEmpty()) break;
      Collections.sort(guestPos);
      int guestNum = map[guestPos.get(0).r][guestPos.get(0).c] - 2;
      //System.out.println("Guest : "+guestPos.get(0).r+" "+guestPos.get(0).c+" "+guestPos.get(0).d);
      int cost = guestPos.get(0).d;
      if (L<cost) break;
      L-=cost;
      int sr = list.get(guestNum).sr;
      int sc = list.get(guestNum).sc;
      int er = list.get(guestNum).er;
      int ec = list.get(guestNum).ec;

      Queue<Pair> que2 = new LinkedList<>();
      boolean[][] visited2 = new boolean[N+1][N+1];

      que2.add(new Pair(sr,sc,0));
      visited2[sr][sc] = true;
      int cost2 = -1;

      while(!que2.isEmpty()) {
        int cr = que2.peek().r;
        int cc = que2.peek().c;
        int cd = que2.peek().d;
        que2.poll();
        if (cr == er && cc == ec) {
          cost2 = cd;
          break;
        }
        for (int i = 0;i<4;i++) {
          int nr = cr+dr[i];
          int nc = cc+dc[i];
          if (nr<1 || nr>N || nc<1 || nc>N) continue;
          if (map[nr][nc]!=1 && !visited2[nr][nc]) {
            visited2[nr][nc] = true;
            que2.add(new Pair(nr,nc,cd+1));
          }
        }
      }
      if (cost2 == -1) break;
      if (cost2>L) break;
      L-=cost2;
      r = er;
      c = ec;
      cntFinish++;
      L+=2*cost2;
      map[sr][sc] = 0;
    }
    if (cntFinish<M) System.out.println(-1);
    else System.out.println(L);
  }
}
