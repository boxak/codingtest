package solutions.implementaition;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindPrimeFromKnumber {

    static final int MAX = 1_000_000_0;

    public static int solution(int n, int k) {
        int answer = 0;

        String kNumber = convert(n,k);

        System.out.println(kNumber);

        boolean[] isNotPrimes = checkPrime();

        Pattern pattern = Pattern.compile("([1-9]{1,})");
        Matcher matcher = pattern.matcher(kNumber);

        ArrayList<String> list = new ArrayList<>();

        while(matcher.find()) {
            String str = matcher.group();
            list.add(str);
        }

        for (String str : list) {
            int index = Integer.parseInt(str);
            if (!isNotPrimes[index]) {
                answer++;
            }
            System.out.println(index + " : " + (isNotPrimes[index] ? "is not prime" : "is prime"));
        }

        return answer;
    }

    public static boolean[] checkPrime() {
        boolean[] isNotPrimes = new boolean[MAX + 1];

        int num = (int)Math.sqrt(MAX);

        isNotPrimes[1] = true;

        for (int i = 2;i<=num;i++) {
            if (!isNotPrimes[i]) {
                for (int j = 2;i*j<=MAX;j++) {
                    isNotPrimes[i*j] = true;
                }
            }
        }

        return isNotPrimes;
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
        System.out.println(solution(99999, 4));
    }

}
