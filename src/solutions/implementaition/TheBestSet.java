package solutions.implementaition;

public class TheBestSet {

    static long answer = Long.MIN_VALUE;
    static int arr[];
    static int answerList[];

    static public int[] solution(int n, int s) {
        int[] answer = {};
        arr = new int[n];
        answerList = new int[n];

        for (int i = s;i>=1;i--) {
            visit(n, s, i, 0, 1);
        }

        answer = new int[n];

        for (int i = 0;i<n;i++) {
            answer[i] = answerList[i];
        }

        return answer;
    }

    static void visit(int n, int s, int cur, int x, long product) {
        if (n-1 == x) {
            arr[x] = cur;
            if (answer < product * cur) {
                answer = product * cur;
                for (int i = 0; i < n; i++) {
                    answerList[i] = arr[i];
                }
            }
            return;
        }

        arr[x] = cur;
        int nextS = s - cur;
        for (int i = nextS;i>=1;i--) {
            visit(n, nextS, i, x+1, product * cur);
        }

    }

    public static void main(String[] args) {
        int[] arr = solution(2, 100_000_000);

        System.out.println("answer");
        for (int i = 0;i<arr.length;i++) {
            System.out.printf("%d ",arr[i]);
        }

    }
}
