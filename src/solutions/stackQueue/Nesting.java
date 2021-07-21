package solutions.stackQueue;

import java.util.Stack;

public class Nesting {
    public int solution(String S) {
        if (S.isEmpty()) return 1;
        Stack<Character> stack = new Stack<>();
        for (int i = 0;i<S.length();i++) {
            if (S.charAt(i) == '(') {
                stack.add(S.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return 0;
                } else {
                    stack.pop();
                }
            }
        }
        if (!stack.isEmpty()) return 0;
        else return 1;
    }
}
