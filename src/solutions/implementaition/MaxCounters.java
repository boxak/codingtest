package solutions.implementaition;

public class MaxCounters {
  public static int[] solution(int N, int[] A) {
    int[] counters = new int[N];
    int max = 0;
    int maxCounter = 0;
    for (int i = 0;i<A.length;i++) {
      int number = A[i] - 1;
      if (number < N) {
        if (counters[number] < maxCounter) {
          counters[number] = maxCounter;
        }
        counters[number]++;
        if (counters[number] > max) {
          max = counters[number];
        }
      } else {
        maxCounter = max;
      }
    }

    for (int i = 0;i<N;i++) {
      if (counters[i] < maxCounter) {
        counters[i] = maxCounter;
      }
    }

    return counters;
  }

  public static void main(String[] args) {
    int[] arr = solution(5, new int[]{3,4,4,6,1,4,4});
    for (int num : arr) {
      System.out.printf("%d ",num);
    }
  }
}
