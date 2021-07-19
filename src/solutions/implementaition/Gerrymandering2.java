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
  static int[] dr = {-1,0,1,0};
  static int[] dc = {0,1,0,-1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    A = new int[N+1][N+1];
    zone = new int[N+1][N+1];
    answer = Integer.MAX_VALUE;

    for (int i = 1;i<=N;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");
      for (int j = 1;j<=N;j++) {
        A[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int x = 1;x<=N;x++) {
      for (int y = 1;y<=N;y++) {
        for (int d1 = 1;d1<=N;d1++) {
          if (isOut(x+d1,y-d1)) continue;
          for (int d2 = 1;d2<=N;d2++) {
            if (isOut(x+d2,y+d2) || isOut(x+d1+d2,y-d1+d2)) continue;
            gerrymandering(x,y,d1,d2);
          }
        }
      }
    }

    System.out.println(answer);

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

    for (int j = y;j>y-d1;j--) {
      for (int i = x+(y-j)+1;i<=N;i++) {
        if (zone[i][j]==5) break;
        zone[i][j] = 5;
      }
    }

    for (int j = y+1;j<y+d2;j++) {
      for (int i = x+(j-y)+1;i<=N;i++) {
        if (zone[i][j]==5) break;
        zone[i][j] = 5;
      }
    }

    //1번 선거구

    for (int i = 1;i<x+d1;i++) {
      for (int j = 1;j<=y;j++) {
        if (zone[i][j]==0) zone[i][j] = 1;
      }
    }

    //2번 선거구
    for (int i = 1;i<=x+d2;i++) {
      for (int j = y+1;j<=N;j++) {
        if (zone[i][j]==0) zone[i][j] = 2;
      }
    }

    //3번 선거구
    for (int i = x+d1;i<=N;i++) {
      for (int j = 1;j<y-d1+d2;j++) {
        if (zone[i][j]==0) zone[i][j] = 3;
      }
    }

    //4번 선거구
    for (int i = x+d2+1;i<=N;i++) {
      for (int j = y-d1+d2;j<=N;j++) {
        if (zone[i][j]==0) zone[i][j] = 4;
      }
    }

    int[] peopleCnt = new int[]{0,0,0,0,0};

    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        int cnt = A[i][j];
        int zoneNum = zone[i][j];
        peopleCnt[zoneNum-1]+=cnt;
      }
    }

    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;

    for (int i = 0;i<5;i++) {
      if (peopleCnt[i]>max) max = peopleCnt[i];
      if (peopleCnt[i]<min) min = peopleCnt[i];
    }

    if (answer>max-min) answer = max - min;

  }

}
