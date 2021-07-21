package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TreeTech {

  static int N,M,K;
  static int[][] A;
  static int[][] map;
  static ArrayList<Tree> trees;
  static ArrayList<Tree> deads;

  static class Tree implements Comparable<Tree> {
    int r;
    int c;
    int age;

    Tree(int r,int c,int age) {
      this.r = r;
      this.c = c;
      this.age = age;
    }

    public int compareTo(Tree tree) {
      return this.age - tree.age;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    A = new int[N+1][N+1];
    map = new int[N+1][N+1];
    trees = new ArrayList<>();
    deads = new ArrayList<>();

    for(int i = 1;i<=N;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      for (int j= 1;j<=N;j++) {
        A[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0;i<M;i++) {
      str = br.readLine();
      st = new StringTokenizer(str," ");
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int age = Integer.parseInt(st.nextToken());
      trees.add(new Tree(r,c,age));
    }

    initialize();

    for (int i = 0;i<K;i++) {
      spring();
      summer();
      fall();
      winter();
    }
    System.out.println(trees.size());
  }

  static void initialize() {
    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        map[i][j] = 5;
      }
    }
  }

  static void spring() {
    ArrayList<Tree> tempList = new ArrayList<>();
    Collections.sort(trees);

    for (Tree tree : trees) {
      int r = tree.r;
      int c = tree.c;
      int age = tree.age;
      if (age>map[r][c]) {
        deads.add(new Tree(r,c,age));
      } else {
        map[r][c]-=age;
        tempList.add(new Tree(r,c,age+1));
      }
    }
    trees.clear();
    for (Tree tree : tempList) trees.add(new Tree(tree.r,tree.c,tree.age));
  }

  static void summer() {
    for (Tree tree :deads) {
      int r = tree.r;
      int c = tree.c;
      int age = tree.age;
      map[r][c]+=age/2;
    }
    deads.clear();
  }

  static void fall() {
    ArrayList<Tree> tempList = new ArrayList<>();

    for (Tree tree : trees) tempList.add(new Tree(tree.r,tree.c,tree.age));

    for (Tree tree : trees) {
      if (tree.age%5==0) {
        int r = tree.r;
        int c = tree.c;
        for (int i=r-1;i<=r+1;i++) {
          for (int j=c-1;j<=c+1;j++) {
            if (i==r && j==c) continue;
            if (i<1 || i>N || j<1 || j>N) continue;
            tempList.add(new Tree(i,j,1));
          }
        }
      }
    }
    trees.clear();
    for (Tree tree : tempList) trees.add(new Tree(tree.r,tree.c,tree.age));
  }

  static void winter() {
    for (int i = 1;i<=N;i++) {
      for (int j = 1;j<=N;j++) {
        map[i][j]+=A[i][j];
      }
    }
  }
}
