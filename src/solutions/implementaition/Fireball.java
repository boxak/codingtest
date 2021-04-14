package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Fireball {

  static class Pair {
    int r;
    int c;
    int m;
    int s;
    int d;
    Pair(int r1,int c1,int m1,int s1,int d1) {
      r=r1;
      c=c1;
      m = m1;
      s = s1;
      d = d1;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    Queue<Pair> que = new LinkedList<>();

    int[] dr = {-1,-1,0,1,1,1,0,-1};
    int[] dc = {0,1,1,1,0,-1,-1,-1};
    ArrayList<Pair>[][] map = new ArrayList[N][N];

    for (int i = 0;i<N;i++) {
      for (int j = 0;j<N;j++) {
        map[i][j] = new ArrayList<>();
      }
    }

    for (int i = 0;i<M;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      que.add(new Pair(r-1,c-1,m,s,d));
    }

    for (int i = 0;i<K;i++) {
      int queSize = que.size();
      for (int h = 0;h<queSize;h++) {
        Pair pair = que.peek();
        que.poll();
        int r = pair.r;
        int c = pair.c;
        int m = pair.m;
        int d = pair.d;
        int s = pair.s;

        int nr = r+dr[d]*s;
        int nc = c+dc[d]*s;
        if (nr < 0) nr = N-((-nr)%N);
        if (nr >= 0) nr = nr%N;
        if (nc < 0) nc = N-((-nc)%N);
        if (nr>=0) nc = nc%N;
        Pair pair2 = new Pair(nr,nc,m,s,d);
        map[nr][nc].add(pair2);
        que.add(pair2);
      }

      for (int j = 0;j<N;j++) {
        for (int k = 0;k<N;k++) {
          ArrayList<Pair> list = map[j][k];
          if (list.size() >= 2) {
            int oddCnt = 0;
            int evenCnt = 0;
            int mSum = 0;
            int sSum = 0;
            int ballCnt = list.size();
            for (Pair pair : list) {
              mSum += pair.m;
              sSum += pair.s;
              if (pair.d % 2 == 0)
                evenCnt++;
              if (pair.d % 2 == 1)
                oddCnt++;
              que.remove(pair);
            }

            for (int f = 0; f < 4; f++) {
              int r = j;
              int c = k;
              int m = mSum / 5;
              int s = sSum / ballCnt;
              int d = 0;
              if (evenCnt == 0 || oddCnt == 0) {
                d = 2 * f;
              } else {
                d = 2 * f + 1;
              }
              if (m > 0) {
                que.add(new Pair(r, c, m, s, d));
              }
            }
          }
          map[j][k].clear();
        }
      }
    }

    int answer = 0;
    for (Pair pair : que) answer+=pair.m;
    System.out.println(answer);
  }
}
