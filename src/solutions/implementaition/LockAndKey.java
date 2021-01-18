package solutions.implementaition;

import java.util.ArrayList;

public class LockAndKey {

  class Pair {
    int r;
    int c;
    Pair(int x, int y) {
      r = x;
      c = y;
    }
  }

  public boolean solution(int[][] key, int[][] lock) {
    boolean answer = true;
    int[][] map1 = key;
    for (int rot = 0; rot < 4; rot++) {
      ArrayList<Pair> list = new ArrayList<>();
      int fr = -1;
      int fc = -1;
      boolean flag = false;
      for (int i = 0;i<map1.length;i++) {
        for (int j = 0;j<map1[0].length;j++) {
          if (map1[i][j] == 1 && !flag) {
            fr = i;
            fc = j;
            flag = true;
            list.add(new Pair(0,0));
          } else if(map1[i][j] == 1 && flag) {
            list.add(new Pair(i-fr,j-fc));
          }
        }
      }

      for (int i = 0;i<lock.length;i++) {
        for (int j=0;j<lock[0].length;j++) {
          int sr = i;
          int sc = j;
          for (int k=0;k<list.size();k++) {
            int r = list.get(k).r;
            int c = list.get(k).c;
            r = r+sr;
            c = c+sc;
            if (r<0 || r>=lock.length || c<0 || c>=lock[0].length) continue;

          }
        }
      }
    }
    return answer;
  }
}
