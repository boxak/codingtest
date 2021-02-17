package solutions.sort;

import java.util.Arrays;

public class Triangle {
    public static int solution(int[] A) {
        Arrays.sort(A);

        boolean flag = false;

        for (int i = 0;i<A.length-2;i++) {
            long a = A[i];
            long b = A[i+1];
            long c = A[i+2];

            boolean flag1 = a+b>c;
            boolean flag2 = a+c>b;
            boolean flag3 = b+c>a;
            if (flag1 && flag2 && flag3) {
                flag = true;
                break;
            }
        }
        return flag ? 1 : 0;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{-10,-3,-1}));
    }
}
