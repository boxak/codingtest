package solutions.greedy;

public class MakeBigNumber {
    public static String solution(String number, int k) {
        String answer = "";
        int len = number.length();
        int returnCnt = len - k;
        int cnt = returnCnt;
        int inx = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < returnCnt; i++) {
            String str = number.substring(inx, len - cnt + 1);
            //System.out.println(str);
            char max = 0;
            int maxInx = -1;
            int strLen = str.length();
            for (int j = 0; j < strLen; j++) {
                if (max<str.charAt(j)) {
                    max = str.charAt(j);
                    maxInx = j +inx;
                }
            }
            sb.append(max);
            inx = maxInx+1;
            cnt--;
        }
        answer = sb.toString();
        return answer;
    }
    public static void main(String[] args) {
        System.out.println(solution("4177252841",4));
    }
}
