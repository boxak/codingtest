package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RemoteCharge {

  static class Battery {
    //(x,y) -> y행 x열
    int x;
    int y;
    int cover;
    int power;
    Battery(int x,int y,int cover,int power) {
      this.x = x;
      this.y =y;
      this.cover = cover;
      this.power = power;
    }
  }

  static int M,A;
  static int[] dx = {0,0,1,0,-1};
  static int[] dy = {0,-1,0,1,0};
  static int answer;
  static int ax,ay,bx,by;
  static int[] Adir,Bdir;
  static Battery[] batteries;

  public static void main(String[] args) throws IOException {
    //BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\enliple\\Downloads\\sample_input (7).txt")));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TestCnt = Integer.parseInt(br.readLine());

    for (int test = 1;test<=TestCnt;test++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");

      M = Integer.parseInt(st.nextToken());
      A = Integer.parseInt(st.nextToken());

      ax = 0;
      ay = 0;
      bx = 9;
      by = 9;
      answer = 0;

      Adir = new int[M];
      Bdir = new int[M];
      batteries = new Battery[A];

      str = br.readLine();
      st = new StringTokenizer(str," ");
      for (int i = 0;i<M;i++) {
        Adir[i] = Integer.parseInt(st.nextToken());
      }

      str = br.readLine();
      st = new StringTokenizer(str," ");
      for (int i = 0;i<M;i++) {
        Bdir[i] = Integer.parseInt(st.nextToken());
      }

      for (int i = 0;i<A;i++) {
        str = br.readLine();
        st = new StringTokenizer(str," ");

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        batteries[i] = new Battery(x-1,y-1,C,P);
      }

      //이동 전(0초) 충전량 합산
      getMaxChargeAtMoment();

      for (int time = 0;time<M;time++) {
        moveA(time);
        moveB(time);
        getMaxChargeAtMoment();
      }

      System.out.println("#"+test+" "+answer);
    }

  }

  static void moveA(int time) {
    int d = Adir[time];
    ax = ax + dx[d];
    ay = ay + dy[d];
  }

  static void moveB(int time) {
    int d = Bdir[time];
    bx = bx + dx[d];
    by = by + dy[d];
  }

  static void getMaxChargeAtMoment() {
    int maxSum = Integer.MIN_VALUE;
    for (int i = 0;i<A;i++) {
      for (int j = 0;j<A;j++) {
        if (getDist(ax,ay,batteries[i].x,batteries[i].y)<=batteries[i].cover
        && getDist(bx,by,batteries[j].x,batteries[j].y)<=batteries[j].cover
        && i==j) {
          if (maxSum<batteries[i].power) maxSum = batteries[i].power;
        } else {
          int sumA = 0;
          int sumB = 0;
          if (getDist(ax,ay,batteries[i].x,batteries[i].y)<=batteries[i].cover) sumA = batteries[i].power;
          if (getDist(bx,by,batteries[j].x,batteries[j].y)<=batteries[j].cover) sumB = batteries[j].power;
          if (sumA+sumB>maxSum) maxSum = sumA + sumB;
        }
      }
    }
    answer+=maxSum;
  }

  static int getDist(int x1,int y1,int x2,int y2) {
    return Math.abs(x2-x1)+Math.abs(y2-y1);
  }
}
