package solutions.implementaition;

public class TheLongestPalindrom {
    public static int solution(String s) {

        int answer = 0;
        int lastInx = 0;

        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();

        for (int i = 0;i<s.length();i++) {
            int start = Math.max(lastInx,i);
            for (int j = start+1;j<=s.length();j++) {
                if (s.charAt(j-1) != s.charAt(i)) continue;
                sb.append(s.charAt(j-1));
                String s1 = s.substring(i,j);
                String s2 = new StringBuffer(s1).reverse().toString();
                if (s1.equals(s2)) {
                    if (answer < j - i) {
                        lastInx = j;
                        answer = j - i;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0;i<2500;i++) {
            sb.append("s");
        }

        int length = solution(sb.toString());
        System.out.println(length);
    }
}
