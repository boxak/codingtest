package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class StudentShark {

  static class Fish implements Comparable<Fish>{
    int inx;
    int r;
    int c;
    int dir;

    Fish(){}

    Fish(int x,int y,int z,int w) {
      inx = x;
      r = y;
      c = z;
      dir = w;
    }

    public int compareTo(Fish fish) {
      return this.inx - fish.inx;
    }

    public boolean equals(Object fish) {
      return ((Fish)fish).inx == this.inx;
    }
  }

  static int[] dr = {0,-1,-1,0,1,1,1,0,-1};
  static int[] dc = {0,0,-1,-1,-1,0,1,1,1};
  static int answer = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Fish[][] map = new Fish[4][4];
    ArrayList<Fish> fishs = new ArrayList<>();
    for (int i = 0;i<4;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");
      for (int j = 0;j<4;j++) {
        int inx = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        map[i][j] = new Fish(inx,i,j,dir);
        fishs.add(new Fish(inx,i,j,dir));
      }
    }

    Collections.sort(fishs);

    int sharkR = 0;
    int sharkC = 0;
    int sharkD = 0;

    dfs(map,fishs,sharkR,sharkC,sharkD,0);

    System.out.println(answer);
  }

  static void dfs(Fish[][] map, ArrayList<Fish> list,int sharkR,int sharkC,int sharkD,int eatSum) {
    sharkD = map[sharkR][sharkC].dir;
    int inx = map[sharkR][sharkC].inx;
    list.remove(map[sharkR][sharkC]);
    map[sharkR][sharkC] = new Fish();
    if (eatSum+inx>answer) answer = eatSum+inx;

    Map<String,Object> objMap = fishMove(map,list,sharkR,sharkC);

    map = (Fish[][]) objMap.get("map");
    list = (ArrayList<Fish>) objMap.get("list");

    for (int i = 1;i<4;i++) {
      int nr = sharkR+dr[sharkD]*i;
      int nc = sharkC+dc[sharkD]*i;
      if (nr<0 || nr>=4 || nc<0 || nc>=4) break;
      if (map[nr][nc].inx!=0) {
        Fish[][] tempMap = new Fish[4][4];
        ArrayList<Fish> tempList = new ArrayList<>();
        for (int j = 0;j<4;j++) {
          for (int k = 0;k<4;k++) {
            Fish tempFish = new Fish(map[j][k].inx,map[j][k].r,map[j][k].c,map[j][k].dir);
            tempMap[j][k] = tempFish;
          }
        }
        for (Fish fish : list) {
          tempList.add(new Fish(fish.inx,fish.r,fish.c,fish.dir));
        }
        dfs(map,list,nr,nc,sharkD,eatSum+inx);
        for (int j = 0;j<4;j++) {
          for (int k = 0;k<4;k++) {
            map[j][k] = new Fish(tempMap[j][k].inx,tempMap[j][k].r,tempMap[j][k].c,tempMap[j][k].dir);
          }
        }
        list.clear();
        for (Fish fish : tempList) {
          list.add(new Fish(fish.inx,fish.r,fish.c,fish.dir));
        }
      }
    }
  }

  static Map<String,Object> fishMove(Fish[][] map, ArrayList<Fish> list,int sharkR,int sharkC) {
    Map<String,Object> objMap = new HashMap<>();
    for (int i = 0;i<list.size();i++) {
      Fish fish = list.get(i);
      int r = fish.r;
      int c = fish.c;
      int d = fish.dir;
      int inx = fish.inx;

      int nr = r+dr[d];
      int nc = c+dc[d];
      while((nr<0 || nr>=4 || nc<0 || nc>=4) || (nr==sharkR && nc==sharkC)) {
        d = d==8 ? 1 : d+1;
        nr = r+dr[d];
        nc = c+dc[d];
      }

      Fish fish2 = map[nr][nc];

      if (fish2.inx!=0) {
        int inx2 = fish2.inx;
        int pos2 = list.indexOf(fish2);
        fish2.r = r;
        fish2.c = c;
        list.set(pos2, fish2);
        list.set(i, new Fish(inx, nr, nc, d));
        map[nr][nc] = new Fish(inx, nr, nc, d);
        map[r][c] = fish2;
      } else {
        list.set(i, new Fish(inx, nr, nc, d));
        map[nr][nc] = new Fish(inx, nr, nc, d);
        map[r][c] = new Fish();
      }
    }
    objMap.put("map",map);
    objMap.put("list",list);
    return objMap;
  }
}
