package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MagicianAndTornado {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[][] Ar = new int[N][N];
    int[] Dr = new int[N*N];
    int answer = 0;

    int[] dr = {-1,0,1,0};
    int[] dc = {0,1,0,-1};

    int[] dr2 = {-1,-2,-1,-1,0,1,1,1,2};
    int[] dc2 = {1,0,0,-1,-2,-1,0,1,0};
    double[] rate = {0.01,0.02,0.07,0.1,0.05,0.1,0.07,0.01,0.02};

    for (int i = 0;i<N;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");
      for (int j = 0;j<N;j++) {
        Ar[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int inx = 0;
    for (int i = 0;i<N/2;i++) {
      int cnt1 = 2*i+1;
      int cnt2 = 2*i+1;
      int cnt3 = 2*i+2;
      int cnt4 = 2*i+2;

      for (int j = 0;j<cnt1;j++) Dr[inx++] = 3;
      for (int j = 0;j<cnt2;j++) Dr[inx++] = 2;
      for (int j = 0;j<cnt3;j++) Dr[inx++] = 1;
      for (int j = 0;j<cnt4;j++) Dr[inx++] = 0;
    }

    for (int i = 0;i<N;i++) {
      Dr[N*N-i-1] = 3;
    }

    int r = N/2;
    int c = N/2;
    for (int i = 0;i<N*N;i++) {
      int d = Dr[i];
      int nr = r+dr[d];
      int nc = c+dc[d];
      if (nr<0 || nr>=N || nc<0 || nc>=N) break;
      r = nr;
      c = nc;
      int y = Ar[r][c];
      for (int j = 0;j<9;j++) {
        int r2 = r+dr2[j];
        int c2 = c+dc2[j];
        int mass = (int)(y*rate[j]);
        if (r2<0 || r2>=N || c2<0 || c2>=N) answer+=mass;
        else Ar[r2][c2]+=mass;
        Ar[r][c]-=mass;
      }


      for (int row = 0;row<N;row++) {
        for (int col = 0;col<N;col++) {
          System.out.printf("%d ",Ar[row][col]);
        }
        System.out.println();
      }
      System.out.println();

    }
    System.out.println(answer);
  }
}
