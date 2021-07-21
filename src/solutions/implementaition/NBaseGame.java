package solutions.implementaition;

import java.util.ArrayList;

public class NBaseGame {
    public static String solution(int n, int t, int m, int p) {
        String answer = "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<t*m;i++) {
            String number = transform(i,n);
            sb.append(number);
        }
        String str = sb.toString();
        System.out.println(str);
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0;i<t;i++) {
            sb2.append(str.charAt(p-1+m*i));
        }
        answer = sb2.toString();
        return answer;
    }

    public static String transform(int number, int n) {
        ArrayList<Integer> tmpList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while(true) {
            tmpList.add(number%n);
            number/=n;
            if (number == 0) break;
        }
        for (int i = tmpList.size()-1;i>=0;i--) {
            int num = tmpList.get(i);
            if (num>=10) {
                sb.append((char)(num-10+'A'));
            } else {
                sb.append(num);
            }
        }
        return sb.toString();
    }

    public static void main(String args[]){
        String a = solution(16,16,2,2);
        System.out.println(a);
    }
}
