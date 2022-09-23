package solutions.implementaition;

import java.util.Arrays;

public class TheBestSet {

    static public int[] solution(int n, int s) {
        int[] answer = {};

        int num = s / n;

        if (num == 0) return new int[]{-1};

        answer = new int[n];

        if (s%n!=0) {
            int num2 = num+1;
            int diff = num2*n - s;

            for (int i = 0;i<diff;i++) {
                answer[i] = num;
            }

            for (int i = diff;i<n;i++) {
                answer[i] = num2;
            }
        } else {
            for (int i = 0;i<n;i++) {
                answer[i] = num;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] arr = solution(3, 16);

        System.out.println("answer");
        for (int i = 0;i<arr.length;i++) {
            System.out.printf("%d ",arr[i]);
        }

    }
}
