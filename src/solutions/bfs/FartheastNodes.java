package solutions.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FartheastNodes {
  public int solution(int n, int[][] edge) {
    int answer = 0;

    ArrayList<ArrayList<Integer>> vector = new ArrayList<>();

    for (int i = 0;i<=n;i++) vector.add(new ArrayList<>());

    for (int i = 0;i<edge.length;i++) {
      int a = edge[i][0];
      int b = edge[i][1];
      vector.get(a).add(b);
      vector.get(b).add(a);
    }

    Queue<Integer> que = new LinkedList<>();
    boolean[] visited = new boolean[n+1];
    que.add(1);
    visited[1] = true;
    int queSize = 0;

    while(!que.isEmpty()) {
      queSize = que.size();
      for (int i = 0;i<queSize;i++) {
        int node = que.peek();
        que.poll();
        for (int j = 0;j<vector.get(node).size();j++) {
          int next = vector.get(node).get(j);
          if (!visited[next]) {
            visited[next] = true;
            que.add(next);
          }
        }
      }
    }

    answer = queSize;

    return answer;
  }
}
