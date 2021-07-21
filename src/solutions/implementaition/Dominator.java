package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;

public class Dominator {

  static class Pair implements Comparable<Pair> {
    int inx;
    int num;
    Pair(int x,int y) {
      inx = x;
      num = y;
    }

    public int compareTo(Pair pair) {
      return this.num - pair.num;
    }
  }

  public static int solution(int[] A) {
    int N = A.length;
    if (N==0) return -1;

    ArrayList<Pair> list = new ArrayList<>();

    for (int i = 0;i<N;i++) {
      list.add(new Pair(i,A[i]));
    }

    Collections.sort(list);

    int answer = -1;
    int cnt = 1;

    for (int i = 1;i<N;i++) {
      if (list.get(i-1).num==list.get(i).num) {
        cnt++;
      } else {
        if (cnt > N/2) {
          answer = list.get(i-1).inx;
          cnt = 1;
          break;
        } else {
          cnt = 1;
        }
      }
    }

    if (cnt > N/2) {
      answer = list.get(list.size()-1).inx;
    }
    return answer;
  }

  public static void main(String args[]) {
    System.out.println(solution(new int[]{0}));
  }
}
