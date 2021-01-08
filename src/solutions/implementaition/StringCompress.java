package solutions.implementaition;

import java.util.LinkedList;
import java.util.Queue;

public class StringCompress {
    public static int solution(String s) {
        int answer = 0;

        int strLen = s.length();
        int minLen = 10000;
        for (int len = 1; len <= strLen; len++) {
            String result = compress(s, len);
            System.out.println(len + " : " + result + ", " + result.length());
            if (!result.isEmpty() && result.length() < minLen) {
                minLen = result.length();
            }
        }
        answer = minLen;
        return answer;
    }

    public static String compress(String s, int len) {
        String temp = "";
        int strLen = s.length();
        Queue<String> que = new LinkedList<>();
        int i = 0;
        for (; i <= strLen - len; i += len) {
            que.add(s.substring(i, i + len));
        }

        if (!s.substring(i).isEmpty()) {
            que.add(s.substring(i));
        }

        String target = que.peek();
        que.poll();
        int count = 1;
        while (!que.isEmpty()) {
            String compare = que.peek();
            que.poll();
            if (target.equals(compare)) {
                count++;
            } else {
                if (count == 1) {
                    temp += target;
                } else {
                    temp += (count + target);
                }
                target = compare;
                count = 1;
            }
        }

        if (count == 1) {
            temp += target;
        } else {
            temp += (count + target);
        }

        return temp;
    }

    public static void main(String[] args) {
        solution("s");
    }
}
