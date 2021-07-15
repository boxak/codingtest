package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MagicianSharkAndBlizzard {

  static int N;
  static int M;
  static int[][] map;
  static int[] dr = {0,-1,1,0,0};
  static int[] dc = {0,0,0,-1,1};
  static int[] dirs;
  static int[] dists;
  static int[] stats = {0,0,0};
  static ArrayList<Marble> marbles;
  static Marble[] positions;

  static class Marble {
    int r;
    int c;
    int num;
    Marble(int r,int c,int num) {
      this.r = r;
      this.c = c;
      this.num = num;
    }

    @Override
    public boolean equals(Object marble) {
      return this.r == ((Marble)marble).r && this.c == ((Marble)marble).c;
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

    map = new int[N+1][N+1];
    dirs = new int[M];
    dists = new int[M];
    marbles = new ArrayList<>();
    positions = new Marble[N*N];

    for (int i = 0;i<N*N;i++) positions[i] = new Marble(0,0,0);

    for (int i = 1;i<=N;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      for (int j = 1;j<=N;j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0;i<M;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");

      dirs[i] = Integer.parseInt(st.nextToken());
      dists[i] = Integer.parseInt(st.nextToken());
    }

    giveNumbers();

    for (int i = 0;i<M;i++) {
      blizzard(dirs[i],dists[i]);
    }

    System.out.println(stats[0]+stats[1]*2+stats[2]*3);

  }

  static void giveNumbers() {
    int r = (N+1)/2;
    int c = (N+1)/2;
    int d = 3;
    int goCount = 0;
    int goCountLimit = 1;
    boolean dirChanged = false;
    // 왼쪽 -> 아래 / -> 오른쪽 -> 위

    for (int i = 0;i<N*N;i++) {
      positions[i] = new Marble(r,c,0);
      if (map[r][c]!=0) {
        marbles.add(new Marble(r,c,map[r][c]));
      }
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

  static void breakMarbles(int dir,int dist) {
    for (int i = 1;i<=dist;i++) {
      int r = ((N+1)/2) + dr[dir]*i;
      int c = ((N+1)/2) + dc[dir]*i;
      if (r<1 || r>N || c<1 || c>N) break;

      int index = marbles.indexOf(new Marble(r,c,map[r][c]));
      if(index==-1) continue;
      marbles.set(index, new Marble(r,c,0));
      map[r][c] = 0;
    }
  }

  static void moveMarbles() {
    ArrayList<Marble> tempArray = new ArrayList<>();
    int[][] tempMap = new int[N+1][N+1];

    for (Marble marble : marbles) {
      if (marble.num!=0) {
        tempArray.add(new Marble(marble.r, marble.c, marble.num));
      }
    }

    marbles.clear();

    for (int i = 0;i< tempArray.size();i++) {
      int r = positions[i+1].r;
      int c = positions[i+1].c;
      int num = tempArray.get(i).num;
      tempMap[r][c] = num;
      marbles.add(new Marble(r,c,num));
    }

    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        map[i][j] = tempMap[i][j];
      }
    }

  }

  static boolean explosion() {
    if (marbles.isEmpty()) return false;
    boolean expolded = false;
    Queue<Marble> tempQue = new LinkedList<>();

    Marble initMarble = marbles.get(0);

    tempQue.add(new Marble(initMarble.r, initMarble.c, initMarble.num));

    for (int i = 1;i<marbles.size();i++) {
      int numInList = tempQue.peek().num;
      int num = marbles.get(i).num;
      if (num == numInList) {
        tempQue.add(new Marble(marbles.get(i).r, marbles.get(i).c, num));
      } else {
        if (tempQue.size()>=4) {
          stats[numInList-1]+=tempQue.size();
          while(!tempQue.isEmpty()) {
            int r = tempQue.peek().r;
            int c = tempQue.peek().c;
            tempQue.poll();

            int index = marbles.indexOf(new Marble(r,c,numInList));
            if (index==-1) continue;
            marbles.set(index, new Marble(r,c,0));
            map[r][c] = 0;
          }
          tempQue.add(new Marble(marbles.get(i).r,marbles.get(i).c,marbles.get(i).num));
          expolded = true;
        } else {
          tempQue.clear();
          tempQue.add(new Marble(marbles.get(i).r,marbles.get(i).c,marbles.get(i).num));
        }
      }
    }

    if (tempQue.size()>=4) {
      int numInList = tempQue.peek().num;
      stats[numInList-1]+=tempQue.size();
      if (tempQue.size() >= 4) {
        while (!tempQue.isEmpty()) {
          int r = tempQue.peek().r;
          int c = tempQue.peek().c;
          tempQue.poll();

          int index = marbles.indexOf(new Marble(r, c, numInList));
          if (index==-1) continue;
          marbles.set(index, new Marble(r, c, 0));
          map[r][c] = 0;
        }
        expolded = true;
      }
    }
    return expolded;
  }

  static void groupingMarbles() {
    if (marbles.isEmpty()) return;
    ArrayList<MarbleInfo> marbleInfos = new ArrayList<>();
    marbleInfos.add(new MarbleInfo(1,marbles.get(0).num));

    for (int i = 1;i< marbles.size();i++) {
      int num = marbles.get(i).num;
      int cnt = marbleInfos.get(marbleInfos.size()-1).cnt;
      int numInList = marbleInfos.get(marbleInfos.size()-1).num;
      if (num == numInList) {
        marbleInfos.set(marbleInfos.size()-1,new MarbleInfo(cnt+1,num));
      } else {
        marbleInfos.add(new MarbleInfo(1,num));
      }
    }

    ArrayList<Integer> tempArray = new ArrayList<>();

    for (MarbleInfo marbleInfo : marbleInfos) {
      tempArray.add(marbleInfo.cnt);
      tempArray.add(marbleInfo.num);
    }

    marbles.clear();
    int[][] tempMap = new int[N+1][N+1];

    int len = tempArray.size() > N*N-1 ? N*N-1 : tempArray.size();
    for (int i = 0;i<len;i++) {
      int r = positions[i+1].r;
      int c = positions[i+1].c;
      int num = tempArray.get(i);

      marbles.add(new Marble(r,c,num));
      tempMap[r][c] = num;
    }

    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        map[i][j] = tempMap[i][j];
      }
    }

  }
}
