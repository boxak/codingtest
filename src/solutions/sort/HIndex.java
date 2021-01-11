package solutions.sort;

import java.util.Arrays;

public class HIndex {
  public static int solution(int[] citations) {
    int answer = 0;

    Arrays.sort(citations);
    for (int i = citations[citations.length-1];i>=0;i--) {
      int h = i;
      if (h>citations.length || h<=0) continue;
      int citation = citations[citations.length - h];
      if (citation>=h) {
        answer = h;
        break;
      }
    }

    return answer;
  }

  public static void main(String[] args) {
    System.out.println(solution(new int[]{0,0,0}));
  }
}
