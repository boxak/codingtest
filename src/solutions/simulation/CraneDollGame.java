package solutions.simulation;

import java.util.Stack;

public class CraneDollGame {
    public int solution(int[][] board, int moves[]) {
        int answer = 0;

        int N = board.length;

        Stack<Integer> stack = new Stack<>();
        int moveLen = moves.length;

        for (int i = 0; i < moveLen; i++) {
            int col = moves[i] - 1;
            int row = choiceDoll(board, col);
            int dollNumber = 0;
            if (row==-1) continue;
            else {
                dollNumber = board[row][col];
                board[row][col] = 0;
            }
            if (!stack.isEmpty()) {
                int topDollNumber = stack.peek();
                if (topDollNumber == dollNumber) {
                    stack.pop();
                    answer += 2;
                } else {
                    stack.push(dollNumber);
                }
            } else {
                stack.push(dollNumber);
            }
        }

        return answer;
    }

    int choiceDoll(int[][] board, int col) {
        int row = -1;
        int N = board.length;
        for (int i = 0; i < N; i++) {
            if (board[i][col]!=0) {
                row = i;
                break;
            }
        }
        return row;
    }
}
