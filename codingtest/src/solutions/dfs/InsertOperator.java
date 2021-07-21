package solutions.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InsertOperator {

  static int maxValue = Integer.MIN_VALUE;
  static int minValue = Integer.MAX_VALUE;
  static int[] operator = new int[4];
  static int[] oper;
  static int[] arr;
  static int N;

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    String str = br.readLine();
    StringTokenizer st = new StringTokenizer(str," ");

    arr = new int[N];
    oper = new int[N-1];

    for (int i = 0;i<N;i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    str = br.readLine();
    st = new StringTokenizer(str," ");

    for (int i = 0;i<4;i++) {
      operator[i] = Integer.parseInt(st.nextToken());
    }

    dfs(0,0,0,0,0);
    System.out.println(maxValue);
    System.out.println(minValue);
  }

  static void dfs(int x,int plus,int minus,int multi,int division) {
    if (x==N-1) {
      calculate();
      return;
    }
    if (plus<operator[0]) {
      oper[x] = 1;
      dfs(x+1,plus+1,minus,multi,division);
    }
    if (minus<operator[1]) {
      oper[x] = 2;
      dfs(x+1,plus,minus+1,multi,division);
    }
    if (multi<operator[2]) {
      oper[x] = 3;
      dfs(x+1,plus,minus,multi+1,division);
    }
    if (division<operator[3]) {
      oper[x] = 4;
      dfs(x+1,plus,minus,multi,division+1);
    }
  }

  static void calculate() {
    int result = arr[0];
    for (int i = 0;i<N-1;i++) {
      if (oper[i]==1) {
        result+=arr[i+1];
      }
      if (oper[i]==2) {
        result-=arr[i+1];
      }
      if (oper[i]==3) {
        result*=arr[i+1];
      }
      if (oper[i]==4) {
        result/=arr[i+1];
      }
    }
    if (result > maxValue) {
      maxValue = result;
    }
    if (result < minValue) {
      minValue = result;
    }
  }
}
