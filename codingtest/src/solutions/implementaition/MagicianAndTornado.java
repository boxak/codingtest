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


    double[] rate = {0.01,0.07,0.1,0.02,0.05,0.1,0.07,0.01,0.02};
    int[] ndr = {1,0,-1,0,-2,-1,0,1,0};
    int[] ndc = {1,1,1,2,0,-1,-1,-1,-2};

    int[] edr = {-1,-1,-1,-2,0,1,1,1,2};
    int[] edc = {-1,0,1,0,2,1,0,-1,0};

    int[] sdr = {-1,0,1,0,2,1,0,-1,0};
    int[] sdc = {-1,-1,-1,-2,0,1,1,1,2};

    int[] wdr = {-1,-1,-1,-2,0,1,1,1,2};
    int[] wdc = {1,0,-1,0,-2,-1,0,1,0};

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
        int r2 = 0;
        int c2 = 0;
        if (d == 0) {
          r2 = r+ndr[j];
          c2 = c+ndc[j];
        }
        if (d == 1) {
          r2 = r + edr[j];
          c2 = c + edc[j];
        }
        if (d == 2) {
          r2 = r+sdr[j];
          c2 = c+sdc[j];
        }
        if (d==3) {
          r2 = r+wdr[j];
          c2 = c+wdc[j];
        }
        int mass = (int)(y*rate[j]);
        if (r2<0 || r2>=N || c2<0 || c2>=N) answer+=mass;
        else Ar[r2][c2]+=mass;
        Ar[r][c]-=mass;
      }
      int nr2 = r+dr[d];
      int nc2 = c+dc[d];
      if (nr2<0 || nr2>=N || nc2<0 || nc2>=N) answer+=Ar[r][c];
      else Ar[nr2][nc2]+=Ar[r][c];
      Ar[r][c] = 0;

    }
    System.out.println(answer);
  }
}
