package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SNS {
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    ArrayList<ArrayList<Integer>> vector = new ArrayList<>();
    ArrayList<Integer> levels = new ArrayList<>();
    boolean[] visited = new boolean[N+1];
    Queue<Integer> que = new LinkedList<>();

    for (int i = 0;i<=N;i++) {
      vector.add(new ArrayList<>());
    }

    for (int i = 0;i<N-1;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");

      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      vector.get(a).add(b);
      vector.get(b).add(a);
    }

    que.add(1);
    visited[1] = true;

    while(!que.isEmpty()) {
      int queSize = que.size();
      levels.add(queSize);
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

    int cnt1 = 0;
    int cnt2 = 0;

    for (int i = 0;i<levels.size();i++) {
      if (i%2==0) cnt1+=levels.get(i);
      else cnt2+=levels.get(i);
    }

    System.out.println(cnt1 < cnt2 ? cnt1 : cnt2);

  }
}
