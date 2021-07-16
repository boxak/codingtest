package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gerrymandering2 {
  static int N;
  static int[][] A;
  static int[][] zone;
  static int answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    A = new int[N+1][N+1];
    zone = new int[N+1][N+1];

    for (int i = 1;i<=N;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");
      for (int j = 1;j<=N;j++) {
        A[i][j] = Integer.parseInt(st.nextToken());
      }
    }

//    for (int x = 1;x<=N;x++) {
//      for (int y = 1;y<=N;y++) {
//        for (int d1 = 1;d1<=N;d1++) {
//          if (isOut(x+d1,y-d1)) continue;
//          for (int d2 = 1;d2<=N;d2++) {
//            if (isOut(x+d2,y+d2) && isOut(x+d1+d2,y-d1+d2)) continue;
//            gerrymandering(x,y,d1,d2);
//          }
//        }
//      }
//    }

    gerrymandering(2,4,2,2);

    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        System.out.printf("%d ",zone[i][j]);
      }
      System.out.println();
    }

  }

  static boolean isOut(int x,int y) {
    return (x<1 || x>N || y<1 || y>N);
  }

  static void gerrymandering(int x,int y,int d1,int d2) {

    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        zone[i][j] = 0;
      }
    }

    int[] p1 = new int[]{x,y};
    int[] p2 = new int[]{x+d1,y-d1};
    int[] p3 = new int[]{x+d1+d2,y-d1+d2};
    int[] p4 = new int[]{x+d2,y+d2};

    int[][] p = new int[][]{p1,p2,p3,p4};

    int[] dr = {1,1,-1,-1};
    int[] dc = {-1,1,1,-1};

    int dir = 0;

    int r = p1[0],c = p1[1];
    zone[r][c] = 5;

    while(r!=p1[0]+1 || c!=p1[1]+1) {
      r = r+dr[dir];
      c = c+dc[dir];
      zone[r][c] = 5;
      if (dir<3 && (r==p[dir+1][0] && c==p[dir+1][1])) {
        dir++;
      }
    }

  }

}
