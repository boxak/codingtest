package solutions.implementaition;

public class MinAvgTwoSlice {
    public static int solution(int[] A) {
        int sums[] = new int[A.length];
        int indexes[] = new int[A.length];
        int inx = 0;
        double minAvg = 10000000;
        sums[1] = A[0]+A[1];
        indexes[1] = 0;

        for (int i = 2;i<A.length;i++) {
            double Avg = (sums[i-1] + A[i]) / ((double)(i - inx + 1));
            double newAvg = (A[i]+A[i-1])/((double)2);
            if (Avg <= newAvg) {
                sums[i] = sums[i-1] + A[i];
            } else {
                sums[i] = A[i-1] + A[i];
                inx = i-1;
            }
            indexes[i] = inx;
        }
        int answer = 0;
        for (int i = 1;i<A.length;i++) {
            double Avg = sums[i]/((double)(i-indexes[i]+1));
            if (minAvg>Avg) {
                minAvg = Avg;
                answer = indexes[i];
            }
        }
        return answer;
    }

    public static void main(String args[]) {
        int a = solution(new int[]{2,2,2,2,2,2,2});
        System.out.println(a);
    }
}
