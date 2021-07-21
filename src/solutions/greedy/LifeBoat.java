package solutions.greedy;

import java.util.Arrays;

public class LifeBoat {
  public static int solution(int[] people, int limit) {
    int answer = 0;

    Arrays.sort(people);
    int left = 0;
    int right = people.length - 1;

    while(right>left) {
      System.out.println(left+" "+right);
      if (people[right]+people[left]<=limit) {
        answer++;
        right--;
        left++;
      } else {
        answer++;
        right--;
      }
    }
    if (right == left) answer++;

    return answer;
  }

  public static void main(String[] args) {
    System.out.println(solution(new int[]{90,90,90,90,10,20,30},100));
  }
}
