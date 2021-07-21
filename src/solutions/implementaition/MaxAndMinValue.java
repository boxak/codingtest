package solutions.implementaition;

import java.util.StringTokenizer;

public class MaxAndMinValue {

  public static String solution(String s) {
    String answer = "";
    String[] strArr = s.split(" ");

    int min = Integer.MAX_VALUE - 1;
    int max = Integer.MIN_VALUE + 1;

    for (String str : strArr) {
      int num = 0;
      num = Integer.parseInt(str);
      if (num > max) {
        max = num;
      }
      if (num < min) {
        min = num;
      }
    }

    answer = min + " " + max;

    return answer;
  }

  public static void main(String[] args) {
    System.out.println(solution("-1 -2 -3 -4"));
  }
}
