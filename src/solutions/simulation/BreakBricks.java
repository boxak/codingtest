package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BreakBricks {

  static int N,W,H;
  static int[][] map;
  static int[][] cmap;
  static int[] arr;
  static int[] dr = {-1,0,1,0};
  static int[] dc = {0,1,0,-1};
  static int answer;

  static class Pair {
    int r;
    int c;
    int num;
    Pair(int r,int c,int num) {
      this.r = r;
      this.c = c;
      this.num = num;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\enliple\\Downloads\\sample_input (5).txt")));
    int TestCnt = Integer.parseInt(br.readLine());
    for (int test = 1;test<=TestCnt;test++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");

      N = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());
      H = Integer.parseInt(st.nextToken());

      map = new int[H][W];
      cmap = new int[H][W];
      arr = new int[N];
      answer = Integer.MAX_VALUE;

      for (int i = 0;i<H;i++) {
        str = br.readLine();
        st = new StringTokenizer(str," ");
        for (int j = 0;j<W;j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      dfs(0);

      System.out.println("#"+test+" "+answer);

    }

  }

  static void dfs(int x) {
    if (x == N) {
      simulation();
      return;
    }
    for (int i = 0;i<W;i++) {
      arr[x] = i;
      dfs(x+1);
      arr[x] = 0;
    }
  }

  static void simulation() {

    for (int i = 0;i<H;i++) {
      for (int j = 0;j<W;j++) {
        cmap[i][j] = map[i][j];
      }
    }

    for (int time = 0;time<N;time++) {
      int shot = arr[time];
      shotBrick(shot);
      bricksFallDown();
    }

    int cnt = 0;

    for (int i = 0;i<H;i++) {
      for (int j = 0;j<W;j++) {
        if (cmap[i][j]>0) cnt++;
      }
    }

    if (cnt<answer) answer = cnt;

  }

  static void shotBrick(int shot) {
    Queue<Pair> que = new LinkedList<>();

    for (int i = 0;i<H;i++) {
      if (cmap[i][shot]>0) {
        que.add(new Pair(i,shot,cmap[i][shot]));
        cmap[i][shot] = 0;
        break;
      }
    }

    while(!que.isEmpty()) {
      Pair pair = que.peek();
      int r = pair.r;
      int c = pair.c;
      int num = pair.num;
      que.poll();

      for (int d = 0;d<4;d++) {
        for (int go = 1;go<num;go++) {
          int nr = r+go*dr[d];
          int nc = c+go*dc[d];
          if (nr<0 || nr>=H || nc<0 || nc>=W) continue;
          if (cmap[nr][nc]>0) {
            que.add(new Pair(nr,nc,cmap[nr][nc]));
            cmap[nr][nc] = 0;
          }
        }
      }

    }

  }

  static void bricksFallDown() {
    for (int j = 0;j<W;j++) {
      for (int i = H-2;i>=0;i--) {
        if (cmap[i][j]>0) {
          int height = i;
          for (int k = i+1;k<H;k++) {
            if (cmap[k][j]>0) break;
            height = k;
          }
          if (height!=i) {
            cmap[height][j] = cmap[i][j];
            cmap[i][j] = 0;
          }
        }
      }
    }
  }
}
