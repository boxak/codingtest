package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NewGame2 {

  static int N,K;
  static int[] dr = {0,0,0,-1,1};
  static int[] dc = {0,1,-1,0,0};
  static int answer;
  static int[][][] map2;
  static int[][] map;
  static Pair[] mal;

  static class Pair {
    int r;
    int c;
    int dir;
    Pair(int r,int c,int dir) {
      this.r = r;
      this.c = c;
      this.dir = dir;
    }
  }

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    answer = 0;
    map = new int[N+1][N+1];
    map2 = new int[N+1][N+1][200];
    mal = new Pair[K];

    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        for (int k = 0;k<200;k++) map2[i][j][k] = -1;
      }
    }

    for (int i = 1;i<=N;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      for (int j = 1;j<=N;j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0;i<K;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int dir = Integer.parseInt(st.nextToken());

      mal[i] = new Pair(r,c,dir);
      map2[r][c][0] = i;
    }

    game();
    if (answer > 10) System.out.println(-1);
    else System.out.println(answer);
  }

  static void game() {
    while(answer<=10) {
      answer++;
      boolean flag = false;
      for (int i = 0;i<K;i++) {
        Pair pair = mal[i];
        int r = pair.r;
        int c = pair.c;
        int dir = pair.dir;

        int nr = r+dr[dir];
        int nc = c+dc[dir];

        int inx = -1;
        for (int j = 0;j<200;j++) {
          if (map2[r][c][j] == i) {
            inx = j;
            break;
          }
        }

        int gubun = getGubun(nr,nc);

        if (gubun == 3) {
          dir = changeDir(dir);
          nr = r+dr[dir];
          nc = c+dc[dir];
          gubun = getGubun(nr,nc);
          if (gubun!=3) {
            move(inx,r,c,nr,nc,false,true);
          } else {
            mal[i] = new Pair(r,c,dir);
          }
        } else if (gubun == 2) {
          move(inx,r,c,nr,nc,true,false);
        } else if (gubun == 1) {
          move(inx,r,c,nr,nc,false,false);
        }

        for (int j = 1;j<=N;j++) {
          for (int k = 1;k<=N;k++) {
            int cnt = 0;
            for (int f = 0;f<200;f++) {
              if (map2[j][k][f] == -1) break;
              cnt++;
            }
            System.out.printf("%d ",cnt);
            if (cnt>=4) {
              flag = true;
              break;
            }
          }
          System.out.println();
        }
        System.out.println();
        if (flag) break;
      }
      System.out.println();
      if (flag) break;
    }
  }

  static void move(int inx,int r,int c,int nr,int nc,boolean reverse,boolean change) {
    ArrayList<Integer> tempList = new ArrayList<>();
    if (!reverse) {
      for (int i = inx;i<200;i++) {
        if (map2[r][c][i] == -1) break;
        tempList.add(map2[r][c][i]);
        map2[r][c][i] = -1;
      }
      int inx2 = -1;
      for (int i = 0;i<200;i++) {
        if (map2[nr][nc][i] == -1) {
          inx2 = i;
          break;
        }
      }

      for (int i = 0;i<tempList.size();i++) {
        map2[nr][nc][i+inx2] = tempList.get(i);
        if (change && i == 0) {
          mal[tempList.get(i)] = new Pair(nr,nc,changeDir(mal[tempList.get(i)].dir));
        }
        else mal[tempList.get(i)] = new Pair(nr,nc,mal[tempList.get(i)].dir);
      }
    } else {
      for (int i = inx;i<200;i++) {
        if (map2[r][c][i] == -1) break;
        tempList.add(map2[r][c][i]);
        map2[r][c][i] = -1;
      }
      int inx2 = -1;
      for (int i = 0;i<200;i++) {
        if (map2[nr][nc][i] == -1) {
          inx2 = i;
          break;
        }
      }

      for (int i = 0;i<tempList.size();i++) {
        map2[nr][nc][inx2+i] = tempList.get(tempList.size()-1-i);
        mal[tempList.get(i)] = new Pair(nr,nc,mal[tempList.get(i)].dir);
      }
    }
  }

  static int getGubun(int r,int c) {
    if (r<1 || r>N || c<1 || c>N) return 3;
    if (map[r][c] == 2) return 3;
    if (map[r][c] == 1) return 2;
    else return 1;
  }

  static int changeDir(int dir) {
    if (dir == 1) return 2;
    if (dir == 2) return 1;
    if (dir == 3) return 4;
    else return 3;
  }
}
