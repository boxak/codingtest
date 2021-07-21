package solutions.dfs;

import java.util.Stack;

public class ColoringBook {

    static class Pair {
        int r;
        int c;

        Pair(int x, int y) {
            r = x;
            c = y;
        }
    }

    static public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        int dr[] = {-1, 0, 1, 0};
        int dc[] = {0, 1, 0, -1};

        Stack<Pair> stack = new Stack<>();
        boolean visited[][] = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    stack.add(new Pair(i, j));
                    numberOfArea++;
                    int sizeOfArea = 1;
                    visited[i][j] = true;
                    while (!stack.isEmpty()) {
                        int r = stack.peek().r;
                        int c = stack.peek().c;
                        boolean flag = false;
                        System.out.println(r + " " + c + " " + picture[r][c]+" " + sizeOfArea);
                        for (int dir = 0; dir < 4; dir++) {
                            int nr = r + dr[dir];
                            int nc = c + dc[dir];
                            if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                            if (picture[nr][nc] == picture[r][c] && !visited[nr][nc]) {
                                stack.add(new Pair(nr, nc));
                                visited[nr][nc] = true;
                                sizeOfArea++;
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) stack.pop();
                    }
                    System.out.println();
                    if (sizeOfArea > maxSizeOfOneArea) maxSizeOfOneArea = sizeOfArea;
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public static void main(String args[]) {
        System.out.println(solution(6, 4, new int[][]{{1, 1, 1, 0}, {1, 1, 1, 0}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}}));
    }
}
