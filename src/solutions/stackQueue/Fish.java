package solutions.stackQueue;

import java.util.Stack;

public class Fish {

    static class Pair {
        int size;
        int dir;
        Pair(int x,int y) {
            size = x;
            dir = y;
        }
    }

    public static int solution(int[] A, int[] B) {
        int N = A.length;
        int cnt = N;
        Stack<Pair> stack = new Stack<>();
        for (int i = 0;i<N;i++) {
            if (stack.isEmpty()) {
                stack.add(new Pair(A[i],B[i]));
            } else {
                if (stack.peek().dir == 1 && B[i] == 0) {
                    while (stack.peek().dir == 1 && B[i] == 0) {
                        int size1 = stack.peek().size;
                        int size2 = A[i];
                        if (size1 > size2) {
                            cnt--;
                            break;
                        } else {
                            cnt--;
                            stack.pop();
                            if (stack.isEmpty()) break;
                            if (!(stack.peek().dir == 1 && B[i] == 0)) {
                                stack.add(new Pair(A[i], B[i]));
                                break;
                            }
                        }
                    }
                } else {
                    stack.add(new Pair(A[i],B[i]));
                }
            }
        }
        return cnt;
    }

    public static void main(String args[]) {
        System.out.println(solution(new int[]{4,3,2,1,5},new int[]{1,1,0,0,0}));
    }
}
