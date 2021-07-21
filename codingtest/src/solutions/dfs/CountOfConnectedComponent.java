package solutions.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CountOfConnectedComponent {

  static int N,M;
  static ArrayList<ArrayList<Integer>> vector;
  static boolean[] visited;
  static int answer = 0;

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    vector = new ArrayList<>();
    visited = new boolean[N+1];

    for (int i = 0;i<=N;i++) {
      vector.add(new ArrayList<>());
    }

    for (int i = 0;i<M;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      vector.get(a).add(b);
      vector.get(b).add(a);
    }

    for (int i = 1;i<=N;i++) {
      if (!visited[i]) {
        answer++;
        dfs(i);
      }
    }

    System.out.println(answer);

  }

  static void dfs(int node) {
    visited[node] = true;
    for (int i = 0;i<vector.get(node).size();i++) {
      int next = vector.get(node).get(i);
      if (!visited[next]) {
        dfs(next);
      }
    }
  }

}
