package solutions.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class DiameterOfTree {

  static class Node {
    int num;
    int weight;
    Node(int a,int b) {
      num = a;
      weight = b;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    Vector<Vector<Node>> vector = new Vector<>();

    for (int i = 0;i<=N;i++) vector.add(new Vector<>());

    for (int i = 0;i<N-1;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");

      int parent = Integer.parseInt(st.nextToken());
      int child = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      vector.get(parent).add(new Node(child,weight));
    }

  }
}
