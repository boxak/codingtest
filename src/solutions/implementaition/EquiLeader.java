package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;

public class EquiLeader {

  class Pair implements Comparable<Pair>{
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

  private int findReader(int[] A,int s,int e) {
    int N = e-s+1;
    if (N == 0) return -1;
    int answer = 0;
    int cnt = 1;
    ArrayList<Pair> list = new ArrayList<>();

    for (int i = s;i<=e;i++) {
      list.add(new Pair(i-s,A[i]));
    }

    Collections.sort(list);

    for (int i = 1;i<N;i++) {
      if (list.get(i).num == list.get(i-1).num) {
        cnt++;
      } else {
        if (cnt > N/2) {
          cnt = 1;
          answer = list.get(i-1).num;
          break;
        } else {
          cnt = 1;
        }
      }
    }

    if (cnt > N/2) {
      answer = list.get(N-1).num;
    }
    return answer;
  }

  public int solution(int[] A) {

    int answer = 0;

    for (int i = 0;i<A.length - 1;i++) {
      int leader1 = findReader(A,0,i);
      int leader2 = findReader(A,i+1,A.length-1);
      if (leader1 == leader2) answer++;
    }
    return answer;
  }
}
