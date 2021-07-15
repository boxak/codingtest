package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MagicianSharkAndBlizzard {

  static int N,M;
  static int[][] marbles;
  static int[][] numbers;
  static int[] directions;
  static int[] speeds;
  static Pair[] positions;
  static int sharkR,sharcC;
  // d : 1 - 위, 2 - 아래, 3 - 왼쪽, 4 - 오른쪽
  static int[] dr = {0,-1,1,0,0};
  static int[] dc = {0,0,0,-1,1};
  static int[] magicStats = {0,0,0};

  static class Pair {
    int r;
    int c;
    Pair(int r,int c) {
      this.r = r;
      this.c = c;
    }
  }

  static class MarbleInfo {
    int cnt;
    int num;
    MarbleInfo(int cnt, int num) {
      this.cnt = cnt;
      this.num = num;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    marbles = new int[N+1][N+1];
    numbers = new int[N+1][N+1];
    positions = new Pair[N*N+1];

    for (int i = 0;i<=N*N;i++) positions[i] = new Pair(0,0);

    speeds = new int[M];
    directions = new int[M];

    for (int i = 1;i<=N;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      for (int j = 1;j<=N;j++) {
        marbles[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0;i<M;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      directions[i] = Integer.parseInt(st.nextToken());
      speeds[i] = Integer.parseInt(st.nextToken());
    }

    sharkR = (N+1)/2;
    sharcC = (N+1)/2;

    giveNumbers();

    for (int i = 0;i<M;i++) {
      blizzard(directions[i],speeds[i]);
    }

    System.out.println(magicStats[0]+magicStats[1]*2+magicStats[2]*3);
  }

  static void giveNumbers() {
    int r = sharkR;
    int c = sharcC;
    int d = 3;
    int goCount = 0;
    int goCountLimit = 1;
    boolean dirChanged = false;
    // 왼쪽 -> 아래 / -> 오른쪽 -> 위

    for (int i = 0;i<N*N;i++) {
      positions[i] = new Pair(r,c);
      r = r + dr[d];
      c = c + dc[d];
      goCount++;
      if (goCount == goCountLimit) {
        goCount = 0;
        if (dirChanged) {
          goCountLimit++;
        }
        d = changeDir(d);
        dirChanged = !dirChanged;
      }
    }

  }

  static int changeDir(int d) {
    int dir = 0;
    if (d==3) dir = 2;
    if (d==2) dir = 4;
    if (d==4) dir = 1;
    if (d==1) dir = 3;

    return dir;
  }

  static void blizzard(int dir,int speed) {
    breakMarbles(dir,speed);
    while(true) {
      moveMarbles();
      boolean isContinue = explosion();
      if (!isContinue) break;
    }
    groupingMarbles();
  }

  static void breakMarbles(int dir,int speed) {
    for (int i = 1;i<=speed;i++) {
      int r = sharkR + dr[dir]*i;
      int c = sharcC + dc[dir]*i;
      if (r<1 || r>N || c<1 || c>N) break;
      marbles[r][c] = 0;
    }
  }

  static void moveMarbles() {
    for (int i = 1;i<N*N;i++) {
      int r = positions[i].r;
      int c = positions[i].c;
      if (marbles[r][c] == 0) {
        for (int j = i+1;j<N*N;j++) {
          int r2 = positions[j].r;
          int c2 = positions[j].c;
          if (marbles[r2][c2]!=0) {
            marbles[r][c] = marbles[r2][c2];
            marbles[r2][c2] = 0;
            break;
          }
        }
      }
    }
  }

  static boolean explosion() {
    ArrayList<Pair> list = new ArrayList<>();
    boolean expolded = false;
    for (int i = 1;i<N*N;i++) {
      if (marbles[positions[i].r][positions[i].c]==0) continue;
      if (list.isEmpty()) {
        list.add(new Pair(positions[i].r,positions[i].c));
      } else {
        Pair last = list.get(list.size()-1);
        int numInList = marbles[last.r][last.c];
        int nowNum = marbles[positions[i].r][positions[i].c];
        if (nowNum == numInList) {
          list.add(new Pair(positions[i].r,positions[i].c));
        } else {
          if (list.size()>=4) {
            for (Pair pair : list) {
              marbles[pair.r][pair.c] = 0;
              magicStats[numInList-1]++;
            }
            expolded = true;
          }
          list.clear();
          list.add(new Pair(positions[i].r,positions[i].c));
        }
      }
    }

    if (list.size()>=4) {
      Pair last = list.get(list.size()-1);
      int numInList = marbles[last.r][last.c];
      for (Pair pair : list) {
        marbles[pair.r][pair.c] = 0;
        magicStats[numInList-1]++;
      }
      expolded = true;
    }

    return expolded;
  }

  static void groupingMarbles() {

    ArrayList<MarbleInfo> marbleInfos = new ArrayList<>();

    int num = marbles[positions[1].r][positions[1].c];
    int cnt = 1;

    for (int i = 2;i<N*N;i++) {
      int r = positions[i].r;
      int c = positions[i].c;
      int nowNum = marbles[r][c];
      if (num == nowNum) {
        cnt++;
      } else {
        marbleInfos.add(new MarbleInfo(cnt,num));
        cnt = 1;
        num = nowNum;
      }
    }

    marbleInfos.add(new MarbleInfo(cnt,num));

    int index = 1;

    int[][] tempMap = new int[N+1][N+1];

    for (MarbleInfo marbleInfo : marbleInfos) {
      int r = positions[index].r;
      int c = positions[index].c;

      tempMap[r][c] = marbleInfo.cnt;

      index++;

      if (index>=N*N) break;

      r = positions[index].r;
      c = positions[index].c;

      tempMap[r][c] = marbleInfo.num;

      index++;
      if (index>=N*N) break;
    }

    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        marbles[i][j] = tempMap[i][j];
      }
    }

  }

}
