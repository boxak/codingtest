package solutions.stackQueue;

import java.util.Stack;

public class RightBracket {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack = new Stack<>();
        for (int i = 0;i<s.length();i++) {
            if (s.charAt(i)=='(') {
                stack.add('(');
            } else {
                if (stack.isEmpty()) {
                    answer = false;
                    break;
                } else {
                    stack.pop();
                }
            }
        }
        if (!stack.isEmpty()) answer = false;

        return answer;
    }
}
