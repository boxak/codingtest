package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProtectionFilm {

  static int D,W,K;
  // 0 : A, 1 : B
  static int[][] map;
  static int[] arr;
  static boolean isFinish;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\enliple\\Downloads\\sample_input (1).txt")));
    int TestCnt = Integer.parseInt(br.readLine());

    for (int test = 1;test<=TestCnt;test++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");

      D = Integer.parseInt(st.nextToken());
      W = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());

      map = new int[D][W];
      arr = new int[D];
      isFinish = false;

      int answer = 0;

      for (int i = 0;i<D;i++) {
        str = br.readLine();
        st = new StringTokenizer(str," ");
        for (int j = 0;j<W;j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      for (int i = 0;i<=D;i++) {
        dfs(0,0,i);
        if (isFinish) {
          answer = i;
          break;
        }
      }

      System.out.println("#"+test+" "+answer);

    }

  }

  static void dfs(int inx,int cnt,int limit) {
    if (isFinish) return;
    if (cnt == limit) {
      if (inspection()) {
        isFinish = true;
      }
      return;
    }
    if (inx == D) return;
    arr[inx] = 1;
    dfs(inx+1, cnt+1, limit);
    arr[inx] = 2;
    dfs(inx + 1, cnt + 1, limit);
    arr[inx] = 0;
    dfs(inx+1, cnt, limit);
  }

  static boolean inspection() {
    boolean flag = true;

    int[][] tempMap = new int[D][W];

    for (int i = 0;i<D;i++) {
      for (int j = 0;j<W;j++) {
        tempMap[i][j] = map[i][j];
      }
    }

    for (int i = 0;i<D;i++) {
      if (arr[i]==1) {
        for (int j = 0;j<W;j++) {
          tempMap[i][j] = 0;
        }
      }
      if (arr[i]==2) {
        for (int j = 0;j<W;j++) {
          tempMap[i][j] = 1;
        }
      }
    }

    for (int j = 0;j<W;j++) {
      int spec = tempMap[0][j];
      int seqCount = 1;
      boolean flag2 = false;
      for (int i = 1;i<D;i++) {
        if (tempMap[i][j]!=spec) {
          spec = tempMap[i][j];
          seqCount = 1;
        } else {
          seqCount++;
        }

        if (seqCount == K) {
          flag2 = true;
          break;
        }
      }
      if (!flag2) {
        flag = false;
        break;
      }
    }

    return flag;
  }
}
