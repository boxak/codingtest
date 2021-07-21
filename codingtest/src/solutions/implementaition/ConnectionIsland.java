package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ConnectionIsland {

  class Pair {
    int v;
    int w;
    int cost;
    Pair(int x,int y,int z) {
      v = x;
      w = y;
      cost = z;
    }
  }

  public int solution(int n, int[][] costs) {
    int answer = 0;

    ArrayList<Pair> vector = new ArrayList<>();

    for (int i = 0;i< costs.length;i++) {
      int a = costs[i][0];
      int b = costs[i][1];
      int c = costs[i][2];
      vector.add(new Pair(a,b,c));
    }

    Collections.sort(vector, new Comparator<Pair>() {
      @Override
      public int compare(Pair o1, Pair o2) {
        if (o1.cost > o2.cost) {
          return 1;
        } else return -1;
      }
    });

    answer = kruskal(n,vector);

    return answer;
  }

  int kruskal(int n,ArrayList<Pair> vector) {
    int answer = 0;

    int[] parents = new int[n];
    for (int i = 0;i<n;i++) parents[i] = i;
    int cntConnection = 0;

    for (int i = 0;i<vector.size();i++) {
      int a = vector.get(i).v;
      int b = vector.get(i).w;
      int cost = vector.get(i).cost;
      int parent1 = findSet(parents,a);
      int parent2 = findSet(parents,b);
      if (parent1==parent2) continue;
      parents[parent1] = parent2;
      answer+=cost;
      cntConnection++;
      if (cntConnection==n-1) break;
    }
    return answer;
  }

  int findSet(int[] parents, int num) {
    if (parents[num]==num) return num;
    return findSet(parents,parents[num]);
  }
}
