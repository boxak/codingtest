package solutions.implementaition;

public class TernaryNumber {
    public static int solution(int n) {
        int answer = 0;

        int[] TernaryNumArr = nToTernary(n);
        String str1 = "";

        for (int num : TernaryNumArr) {
            System.out.printf("%d", num);
            str1 += num;
        }
        System.out.println();

        answer = calculate(str1);

        return answer;
    }

    static int[] nToTernary(int n) {
        int[] arr = new int[10000];
        int cnt = 0;
        while (n > 0) {
            arr[cnt] = n % 3;
            n /= 3;
            cnt++;
        }

        int[] arr2 = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            arr2[i] = arr[i];
        }
        return arr2;
    }

    static int calculate(String str) {
        int sum = 0;
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            int power = (int)Math.pow(3.0,strLen-i-1);
            int num = str.charAt(i) - '0';
            sum+=power*num;
        }
        return sum;
    }

    public static void main(String args[]) {
        System.out.println(solution( 100000000));
    }
}
