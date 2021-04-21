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
  static int[][] map;
  static ArrayList<Pair> mals;
  static ArrayList<Integer>[][] map2;
  static int turnCnt;

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

    map = new int[N+1][N+1];
    map2 = new ArrayList[N+1][N+1];
    turnCnt = 0;
    mals = new ArrayList<>();

    for (int i = 1;i<=N;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      for (int j = 1;j<=N;j++) {
        map2[i][j] = new ArrayList<>();
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0;i<K;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int dir = Integer.parseInt(st.nextToken());
      mals.add(new Pair(r,c,dir));
      map2[r][c].add(i);
    }

    startGame();


    if (turnCnt>1000) System.out.println(-1);
    else System.out.println(turnCnt);
  }

  static void startGame() {
    while(turnCnt<=1000) {
      turnCnt++;
      boolean flag = false;
      for (int i = 0;i< K;i++) {
        Pair pair = mals.get(i);
        int r = pair.r;
        int c = pair.c;
        int dir = pair.dir;
        ArrayList<Integer> list = map2[r][c];

        int inx = list.indexOf(i);
        ArrayList<Integer> temp = new ArrayList<>();
        for (int j = inx;j<list.size();j++) {
          temp.add(list.get(j));
        }

        int nr = r+dr[dir];
        int nc = c+dc[dir];

        if (nr<1 || nr>N || nc<1 || nc>N) {
          dir = changeDir(dir);
          nr = r+dr[dir];
          nc = c+dc[dir];
          if (map[nr][nc]!=2) {
            for (int j = 0;j<temp.size();j++) {
              map2[nr][nc].add(temp.get(j));
              map2[r][c].remove((Integer)temp.get(j));
              int inx2 = temp.get(j);
              Pair pair2 = mals.get(inx2);
              mals.set(inx2, new Pair(nr,nc,pair2.dir));
            }
          }
        } else {
          if (map[nr][nc] == 2) {
            dir = changeDir(dir);
            nr = r+dr[dir];
            nc = c+dc[dir];
            if (!(nr<1 || nr>N || nc<1 || nc>N) && map[nr][nc]!=2) {
              for (int j = 0;j<temp.size();j++) {
                map2[nr][nc].add(temp.get(j));
                map2[r][c].remove((Integer)temp.get(j));
                int inx2 = temp.get(j);
                Pair pair2 = mals.get(inx2);
                mals.set(inx2, new Pair(nr,nc,pair2.dir));
              }
            }
          } else if (map[nr][nc] == 1) {
            for (int j = temp.size()-1;j>=0;j--) {
              map2[nr][nc].add(temp.get(j));
              map2[r][c].remove((Integer)temp.get(j));
              int inx2 = temp.get(j);
              Pair pair2 = mals.get(inx2);
              mals.set(inx2, new Pair(nr,nc,pair2.dir));
            }
          } else if (map[nr][nc] == 0) {
            for (int j = 0;j<temp.size();j++) {
              map2[nr][nc].add(temp.get(j));
              map2[r][c].remove((Integer)temp.get(j));
              int inx2 = temp.get(j);
              //System.out.println("zzz : "+temp.get(j)+" "+inx2);
              Pair pair2 = mals.get(inx2);
              mals.set(inx2, new Pair(nr,nc,pair2.dir));
            }
          }
        }
        for (int k = 1;k<=N;k++) {
          for (int j = 1;j<=N;j++) {
            if (map2[k][j].size()>=4) {
              flag = true;
              break;
            }
          }
        }
        if (flag) break;
      }
      if (flag) break;
    }
  }

  static int changeDir(int dir) {
    if (dir == 1) return 2;
    if (dir == 2) return 1;
    if (dir == 3) return 4;
    else return 3;
  }
}
