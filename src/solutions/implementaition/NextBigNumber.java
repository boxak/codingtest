package solutions.implementaition;

public class NextBigNumber {
    public static int solution(int n) {
        int answer = 0;

        int nextNum = n+1;
        String nToBinary = Integer.toBinaryString(n);
        int cnt1 = 0;
        for (int i = 0;i<nToBinary.length();i++) {
            if (nToBinary.charAt(i) == '1') cnt1++;
        }

        while(true) {
            String str = Integer.toBinaryString(nextNum);
            int cnt2 = 0;
            for (int i = 0;i<str.length();i++) {
                if (str.charAt(i) == '1') cnt2++;
            }
            if (cnt1 == cnt2) {
                answer = nextNum;
                break;
            }
            nextNum++;
        }

        return answer;
    }

    public static void main(String args[]) {
        System.out.println(solution(15));
    }
}
