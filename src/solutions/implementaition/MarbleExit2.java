package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MarbleExit2 {

  static int N,M;
  static int[] dr = {-1,0,1,0};
  static int[] dc = {0,1,0,-1};
  static int answer;
  static char[][] map;
  static int Rr,Br,Rc,Bc;
  static int r1,r2,c1,c2;
  static int[] arr;
  static boolean flag1,flag2;

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new char[N+1][M+1];
    answer = Integer.MAX_VALUE;
    arr = new int[10];

    for (int i = 1;i<=N;i++) {
      str = br.readLine();
      for (int j = 1;j<=M;j++) {
        map[i][j] = str.charAt(j-1);
        if (map[i][j] == 'R') {
          Rr = i;
          Rc = j;
          map[i][j] = '.';
        }
        if (map[i][j] == 'B') {
          Br = i;
          Bc = j;
          map[i][j] = '.';
        }
      }
    }

    dfs(0);

    if (answer == Integer.MAX_VALUE) System.out.println(-1);
    else System.out.println(answer);

  }

  static void dfs(int x) {
    if (x == 10) {
      simulation();
      return;
    }
    for (int i = 0;i<4;i++) {
      arr[x] = i;
      dfs(x+1);
    }
  }

  static void simulation() {
    r1 = Rr;
    c1 = Rc;
    r2 = Br;
    c2 = Bc;
    flag1 = false;
    flag2 = false;
    for (int count = 1;count<=10;count++) {
      int dir = arr[count-1];
      int gubun = getGubun(dir,r1,c1,r2,c2);
      if (gubun == 1) {
        redMove(dir);
        if (flag1) {
          r1 = -1;
          c1 = -1;
        }
        blueMove(dir);
      } else if (gubun == 2) {
        blueMove(dir);
        if (flag2) {
          r2 = -1;
          c2 = -1;
        }
        redMove(dir);
      }
      if (flag1 || flag2) {
        if (flag1 && !flag2) {
          if (answer > count)
            answer = count;
        }
        break;
      }
    }
  }

  static void redMove(int d) {
    while(true) {
      int nr = r1+dr[d];
      int nc = c1+dc[d];
      if (map[nr][nc] == '#') break;
      if (map[nr][nc] == 'O') {
        flag1 = true;
        break;
      }
      if (nr==r2 && nc==c2) break;
      r1 = nr;
      c1 = nc;
    }
  }

  static void blueMove(int d) {
    while(true) {
      int nr = r2 + dr[d];
      int nc = c2 + dc[d];
      if (map[nr][nc] == '#') break;
      if (map[nr][nc] == 'O') {
        flag2 = true;
        break;
      }
      if (r1==nr && c1==nc) break;
      r2 = nr;
      c2 = nc;
    }
  }

  //1이면 빨간 구슬 먼저 이동, 2이면 파란 구슬 먼저 이동
  static int getGubun(int dir,int r1,int c1,int r2,int c2) {
    int result = 0;
    if (dir == 0) {
      if (r1<=r2) result = 1;
      else result = 2;
    }
    else if (dir == 1) {
      if (c1>=c2) result = 1;
      else result = 2;
    }
    else if (dir == 2) {
      if (r1>=r2) result = 1;
      else result = 2;
    }
    else if (dir == 3) {
      if (c1<=c2) result = 1;
      else result = 2;
    }
    return result;
  }
}
