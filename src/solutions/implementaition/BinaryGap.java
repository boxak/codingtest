package solutions.implementaition;

public class BinaryGap {
    public int solution(int N) {
        int answer = 0;
        String binaryStr = Integer.toBinaryString(N);

        int cnt = 0;
        for (int i = 0;i<binaryStr.length();i++) {
            if (binaryStr.charAt(i) == '0') {
                cnt++;
            } else if (cnt > 0 && binaryStr.charAt(i) == '1') {
                if (answer<cnt) {
                    answer = cnt;
                }
                cnt = 0;
            }
        }
        return answer;
    }
}
