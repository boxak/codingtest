package solutions.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HomeSecurityService {

  static int N,M;
  static int[][] map;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\enliple\\Downloads\\sample_input (2).txt")));

    int TestCnt = Integer.parseInt(br.readLine());

    for (int test = 1;test<=TestCnt;test++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      map = new int[N+1][N+1];

      for (int i = 1;i<=N;i++) {
        str = br.readLine();
        st = new StringTokenizer(str," ");
        for (int j = 1;j<=N;j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int answer = getMaxHouseCnt();
      System.out.println("#"+test+" "+answer);
    }

  }

  static int getMaxHouseCnt() {
    int maxCnt = Integer.MIN_VALUE;

    for (int r = 1;r<=N;r++) {
      for (int c = 1;c<=N;c++) {
        for (int dist = 0;dist<=2*N-1;dist++) {
          int cnt = getHouseCnt(r,c,dist);
          int cost = (dist+1)*(dist+1) + dist*dist;
          int revenue = cnt*M - cost;
          if (revenue>=0 && cnt>maxCnt) maxCnt = cnt;
        }
      }
    }

    return maxCnt;
  }

  static int getHouseCnt(int r,int c,int dist) {
    int houseCnt = 0;
    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        if (map[i][j]==1 && getDist(r,c,i,j)<=dist) houseCnt++;
      }
    }

    return houseCnt;
  }

  static int getDist(int r1,int c1,int r2,int c2) {
    return Math.abs(r2-r1) + Math.abs(c2-c1);
  }
}
