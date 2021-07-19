package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BuildAirstrip {

  static int N,X;
  static int[][] map;
  static int[][] load1;
  static int[][] load2;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\enliple\\Downloads\\sample_input (3).txt")));

    int TestCnt = Integer.parseInt(br.readLine());

    for (int test=1;test<=TestCnt;test++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");

      N = Integer.parseInt(st.nextToken());
      X = Integer.parseInt(st.nextToken());
      map = new int[N+1][N+1];
      load1 = new int[N+1][N+1];
      load2 = new int[N+1][N+1];
      for (int i = 1;i<=N;i++) {
        str = br.readLine();
        st = new StringTokenizer(str," ");
        for (int j = 1;j<=N;j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int answer = getAirstripCnt();

      System.out.println("#"+test+" "+answer);

    }

  }

  static int getAirstripCnt() {
    removeGaps();
    return countAirstips();
  }

  static void removeGaps() {
    //가로 방향 경사로 만들기
    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        int height1 = map[i][j];
        for (int k = -1;k<=1;k+=2) {
          if (j+k<1 || j+k>N) continue;
          int height2 = map[i][j+k];
          if (height1 - height2 == 1 && j+k*X>=1 && j+k*X<=N) {
            boolean flag = true;

            for (int f=1;f<=X;f++) {
              if (map[i][j+k]!=map[i][j+k*f]) {
                flag = false;
                break;
              }
            }
            if (flag) {
              for (int f = 1; f <= X; f++) {
                load1[i][j + f * k]++;
              }
            }
          }
        }
      }
    }

    //세로 방향 경사로 만들기
    for (int j = 1;j<=N;j++) {
      for (int i = 1;i<=N;i++) {
        int height1 = map[i][j];
        for (int k = -1;k<=1;k+=2) {
          if (i+k<1 || i+k>N) continue;
          int height2 = map[i+k][j];
          if (height1 - height2 == 1 && i+k*X>=1 && i+k*X<=N) {
            boolean flag = true;

            for (int f=1;f<=X;f++) {
              if (map[i+k][j]!=map[i+k*f][j]) {
                flag = false;
                break;
              }
            }

            if (flag) {
              for (int f = 1; f <= X; f++) {
                load2[i + f * k][j]++;
              }
            }
          }
        }
      }
    }
  }

  static int countAirstips() {
    int count = 0;
    for (int i = 1;i<=N;i++) {
      boolean flag = true;
      for (int j = 1;j<N;j++) {
        if (Math.abs(map[i][j]-map[i][j+1])>=2) {
          flag = false;
          break;
        }
        if (load1[i][j]>=2 || load1[i][j+1]>=2) {
          flag = false;
          break;
        }
        if (Math.abs(map[i][j]-map[i][j+1])==1) {
          if (map[i][j]>map[i][j+1] && load1[i][j+1]!=1) {
            flag = false;
            break;
          }
          if (map[i][j]<map[i][j+1] && load1[i][j]!=1) {
            flag = false;
            break;
          }
        }
      }
      if (flag) count++;
    }

    for (int j = 1;j<=N;j++) {
      boolean flag = true;
      for (int i = 1;i<N;i++) {
        if (Math.abs(map[i][j]-map[i+1][j])>=2) {
          flag = false;
          break;
        }
        if (load2[i][j]>=2 || load2[i+1][j]>=2) {
          flag = false;
          break;
        }
        if (Math.abs(map[i][j]-map[i+1][j])==1) {
          if (map[i][j]>map[i+1][j] && load2[i+1][j]!=1) {
            flag = false;
            break;
          }
          if (map[i][j]<map[i+1][j] && load2[i][j]!=1) {
            flag = false;
            break;
          }
        }
      }
      if (flag) count++;
    }

    return count;
  }

}
