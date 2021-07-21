package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MinimumCost {

  static class Pair {
    int v;
    int cost;

    Pair(int x,int y) {
      v = x;
      cost = y;
    }
  }

  static final int INF = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());

    ArrayList<ArrayList<Pair>> vector = new ArrayList<>();

    for (int i = 0;i<=N;i++) vector.add(new ArrayList<>());

    for (int i = 0;i<M;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str, " ");
      int v1 = Integer.parseInt(st.nextToken());
      int v2 = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      vector.get(v1).add(new Pair(v2,cost));

    }

    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");
    int from = Integer.parseInt(st.nextToken());
    int to = Integer.parseInt(st.nextToken());

    int[] dist = new int[N+1];
    boolean[] check = new boolean[N+1];

    for (int i = 1;i<=N;i++) dist[i] = INF;

    dist[from] = 0;

    for (int i = 1;i<=N;i++) {
      int minValue = INF;
      int minInx = -1;
      for (int j = 1;j<=N;j++) {
        if (!check[j] && minValue>dist[j]) {
          minValue = dist[j];
          minInx = j;
        }
      }
      check[minInx] = true;

      if (minInx==to) break;

      ArrayList<Pair> temp = vector.get(minInx);

      for (int j = 0;j<temp.size();j++) {
        int node = temp.get(j).v;
        int cost = temp.get(j).cost;
        if (dist[node]>dist[minInx]+cost) {
          dist[node] = dist[minInx]+cost;
        }
      }
    }

    System.out.println(dist[to]);
  }
}
