package solutions.implementaition;

public class JadenCaseString {
    public static String solution(String s) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        String strArr[]= s.split("");
        sb.append(strArr[0].toUpperCase());
        for (int i = 1;i< strArr.length;i++) {
            if (" ".equals(strArr[i-1])) {
                sb.append(strArr[i].toUpperCase());
            } else {
                sb.append(strArr[i].toLowerCase());
            }
        }
        answer = sb.toString();
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution("p COdEDDSD"));
    }
}
