package solutions.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class FindParentsOfNodes {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] parents = new int[N+1];

    Vector<Vector<Integer>> vector = new Vector<>();

    for (int i = 0;i<=N;i++) {
      vector.add(new Vector<>());
    }

    for (int i = 0;i<N-1;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      vector.get(a).add(b);
      vector.get(b).add(a);

    }

    Queue<Integer> que = new LinkedList<>();
    boolean[] visited = new boolean[N+1];

    que.add(1);
    visited[1] = true;

    while(!que.isEmpty()) {
      int node = que.peek();
      que.poll();
      Vector<Integer> tempVector = vector.get(node);

      for (int i = 0;i<tempVector.size();i++) {
        int next = tempVector.get(i);
        if (!visited[next]) {
          visited[next] = true;
          que.add(next);
          parents[next] = node;
        }
      }

    }

    for (int i = 2;i<=N;i++) {
      System.out.println(parents[i]);
    }

  }
}
