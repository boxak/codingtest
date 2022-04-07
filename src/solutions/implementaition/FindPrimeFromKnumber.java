package solutions.implementaition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindPrimeFromKnumber {

    public static int solution(int n, int k) {
        int answer = 0;

        String kNumber = convert(n,k);

        Pattern pattern = Pattern.compile("([1-9]{1,})");
        Matcher matcher = pattern.matcher(kNumber);

        while(matcher.find()) {
            String str = matcher.group();
            if (isPrime(Long.parseLong(str))) answer++;
        }

        return answer;
    }

    public static boolean isPrime(long n) {
        if (n == 1) return false;
        else if(n == 2) return true;
        else {
            for (int i = 2;i<Math.sqrt(n);i++) {
                if (n%i==0) return false;
            }
            return true;
        }
    }

    public static String convert(int n, int k) {
        StringBuilder sb = new StringBuilder();

        while(n>0) {
            sb.append(n%k);
            n/=k;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println(solution(4*4*4*4*4*4*4*4*4-1, 4));
        long endTime = System.currentTimeMillis();
        System.out.println(4*4*4*4*4*4*4*4*4-1);
        System.out.println("elapsed : " + (endTime - startTime));
    }

}
