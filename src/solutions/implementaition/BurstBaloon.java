package solutions.implementaition;

public class BurstBaloon {
  public static int solution(int[] a) {
    int answer = 0;

    int minValue = Integer.MAX_VALUE;
    int minInx = -1;
    for (int i = 0;i<a.length;i++) {
      if (a[i]<minValue) {
        minValue = a[i];
        minInx = i;
      }
    }

    int tmpMin = Integer.MAX_VALUE;
    answer = 1;
    for (int i = 0;i< minInx;i++) {
      if (a[i]<tmpMin) {
        tmpMin = a[i];
        answer++;
      }
    }
    tmpMin = Integer.MAX_VALUE;
    for (int i = a.length-1;i>minInx;i--) {
      if (a[i]<tmpMin) {
        tmpMin = a[i];
        answer++;
      }
    }

    return answer;
  }

  public static void main(String args[]) {
    System.out.println(solution(new int[]{
        9,-1,5
    }));
  }
}
