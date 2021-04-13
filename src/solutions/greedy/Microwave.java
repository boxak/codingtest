package solutions.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Microwave {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    int[] times = new int[]{300,60,10};

    int[] answer = new int[]{0,0,0};

    for (int i = 0;i<3;i++) {
      int time = times[i];
      answer[i]=T/time;
      T=T%time;
    }

    if (T!=0) System.out.println(-1);
    else {
      for (int i = 0;i<3;i++) {
        System.out.printf("%d ",answer[i]);
      }
    }
  }
}
