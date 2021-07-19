package solutions.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Cook {

  static int N;
  static int[][] foods;
  static int answer;
  static int[] arr;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\enliple\\Downloads\\sample_input (4).txt")));
    int TestCnt = Integer.parseInt(br.readLine());

    for (int test = 1;test<=TestCnt;test++) {
      N = Integer.parseInt(br.readLine());
      foods = new int[N+1][N+1];
      arr = new int[N+1];

      for (int i = 1;i<=N;i++) {
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        for (int j = 1;j<=N;j++) {
          foods[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      answer = Integer.MAX_VALUE;

      dfs(1,0,0);
      System.out.println("#"+test+" "+answer);
    }

  }

  static void dfs(int inx,int cntA,int cntB) {
    if (cntA>N/2 || cntB>N/2) return;
    if (cntA==N/2 && cntB==N/2) {
      int diff = getDiff();
      if (diff<answer) answer = diff;
      return;
    }
    arr[inx] = 1;
    dfs(inx+1,cntA+1,cntB);
    arr[inx] = 2;
    dfs(inx+1,cntA,cntB+1);
  }

  static int getDiff() {
    int sumA = 0;
    int sumB = 0;

    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        if (i==j) continue;
        if (arr[i]==1 && arr[j]==1) sumA+=foods[i][j];
      }
    }

    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        if (i==j) continue;
        if (arr[i]==2 && arr[j]==2) sumB+=foods[i][j];
      }
    }

    return Math.abs(sumA-sumB);
  }
}
