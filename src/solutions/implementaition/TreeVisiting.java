package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class TreeVisiting {

  static class Node {
    String left;
    String right;
    Node(String a,String b) {
      left = a;
      right = b;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());

    HashMap<String, Node> treeMap = new HashMap<>();

    for (int i = 0;i<N;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");

      String parent = st.nextToken();
      String left = st.nextToken();
      String right = st.nextToken();

      treeMap.put(parent,new Node(left, right));
    }

    visiting1(treeMap,"A");
    System.out.println();
    visiting2(treeMap,"A");
    System.out.println();
    visiting3(treeMap,"A");

  }

  static void visiting1(HashMap<String, Node> treeMap, String parent) {
    System.out.print(parent);
    String left = treeMap.get(parent).left;
    String right = treeMap.get(parent).right;
    if (!".".equals(left)) visiting1(treeMap, left);
    if (!".".equals(right)) visiting1(treeMap, right);
  }

  static void visiting2(HashMap<String, Node> treeMap, String parent) {
    String left = treeMap.get(parent).left;
    String right = treeMap.get(parent).right;
    if (!".".equals(left)) visiting2(treeMap, left);
    System.out.print(parent);
    if (!".".equals(right)) visiting2(treeMap, right);
  }

  static void visiting3(HashMap<String, Node> treeMap, String parent) {
    String left = treeMap.get(parent).left;
    String right = treeMap.get(parent).right;
    if (!".".equals(left)) visiting3(treeMap, left);
    if (!".".equals(right)) visiting3(treeMap, right);
    System.out.print(parent);;
  }
}
