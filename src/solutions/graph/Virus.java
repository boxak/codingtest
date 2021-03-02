package solutions.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Virus {
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());

    ArrayList<Integer>[] vector = new ArrayList[N+1];
    for (int i = 1;i<=N;i++) {
      vector[i] = new ArrayList<>();
    }

    for (int i = 0;i<M;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      vector[a].add(b);
      vector[b].add(a);
    }

    int answer = bfs(vector, N);
    System.out.println(answer);
  }

  static int bfs(ArrayList<Integer>[] vector, int N) {
    boolean visited[] = new boolean[N+1];
    Queue<Integer> que = new LinkedList<>();

    que.add(1);
    visited[1] = true;
    int cnt = 0;

    while(!que.isEmpty()) {
      int node = que.peek();
      que.poll();
      for (int i = 0;i<vector[node].size();i++) {
        int next = vector[node].get(i);
        if (!visited[next]) {
          visited[next] = true;
          cnt++;
          que.add(next);
        }
      }
    }
    return cnt;
  }
}
