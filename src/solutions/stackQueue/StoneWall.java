package solutions.stackQueue;

import java.util.Stack;

public class StoneWall {
    public int solution(int[] H) {
        Stack<Integer> stack = new Stack<>();
        stack.add(H[0]);
        int answer = 1;
        for (int i = 1;i<H.length;i++) {
            int height1 = stack.peek();
            int height2 = H[i];
            if (height1 == height2) {
            } else if (height1 > height2) {
                while (stack.peek() > height2) {
                    stack.pop();
                    if (stack.isEmpty()) break;
                }
                if (!stack.isEmpty()) {
                    if (stack.peek() != height2) {
                        stack.add(height2);
                        answer++;
                    }
                } else {
                    stack.add(height2);
                    answer++;
                }
            } else {
                stack.add(H[i]);
                answer++;
            }
        }
        return answer;
    }
}
