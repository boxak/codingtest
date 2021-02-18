package solutions.stackQueue;

import java.util.Stack;

public class Brackets {

    public int solution(String S) {
        Stack<Character> stack = new Stack<>();

        int flag = 1;

        for (int i = 0;i<S.length();i++) {
            if (S.charAt(i)=='(' || S.charAt(i)=='{' || S.charAt(i)=='[') {
                stack.add(S.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    flag = 0;
                    break;
                } else {
                    if (S.charAt(i)==')' && stack.peek()!='(') {
                        flag = 0;
                        break;
                    } else if(S.charAt(i)=='}' && stack.peek()!='{') {
                        flag = 0;
                        break;
                    } else if(S.charAt(i)==']' && stack.peek()!='[') {
                        flag = 0;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }
        }
        if (!stack.isEmpty()) flag = 0;
        return flag;
    }

}
