package solutions.dfs;

public class DisapperingBlocks {

    static int minMove = Integer.MAX_VALUE;
    static int maxMove = Integer.MIN_VALUE;

    static int[] dr = new int[]{-1,0,1,0};
    static int[] dc = new int[]{0,1,0,-1};

    static public int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;

        dfs(board, aloc[0], aloc[1], bloc[0], bloc[1], 0, 0, true);

        answer = maxMove + minMove;

        return answer;
    }

    static public void dfs(int[][] board, int r1, int c1, int r2, int c2, int aMove, int bMove, boolean aTurn) {
        int N = board.length;
        int M = board[0].length;

        System.out.println("A : (" + r1 + ", "+ c1 + ") B : (" + r2 + ", "+c2+")");
        System.out.println("A move : " + aMove +", B move : " + bMove);

        for (int i = 0;i<N;i++) {
            for (int j = 0;j<M;j++) {
                System.out.printf("%d ",board[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        if (board[r1][c1] == 0 || board[r2][c2] == 0) {
            if (board[r1][c1] == 0) {
                if (bMove < minMove) {
                    minMove = bMove;
                    maxMove = aMove;
                } else if (bMove == minMove) {
                    if (aMove > maxMove) {
                        maxMove = aMove;
                    }
                }
            } else if (board[r2][c2] == 0) {
                if (aMove < minMove) {
                    minMove = aMove;
                    maxMove = bMove;
                } else if (aMove == minMove) {
                    if (bMove > maxMove) {
                        maxMove = bMove;
                    }
                }
            }

            return;
        }

        boolean ableA = canMove(board, r1, c1);
        boolean ableB = canMove(board, r2, c2);
//
        if (!ableA || !ableB) {
            if (!ableA) {
                if (bMove < minMove) {
                    minMove = bMove;
                    maxMove = aMove;
                    System.out.println("answer : " + (minMove + maxMove));
                } else if (bMove == minMove) {
                    if (aMove > maxMove) {
                        maxMove = aMove;
                    }
                    System.out.println("answer : " + (minMove + maxMove));
                }
            } else if (!ableB) {
                if (aMove < minMove) {
                    minMove = aMove;
                    maxMove = bMove;
                } else if (aMove == minMove) {
                    if (bMove > maxMove) {
                        maxMove = bMove;
                    }
                }
            }

            return;
        }

        if (aTurn) {
            for (int d = 0; d<4;d++) {
                int nr = r1 + dr[d];
                int nc = c1 + dc[d];
                if (!isOutside(N, M, nr, nc)) {
                    if (board[nr][nc] == 1) {
                        board[r1][c1] = 0;
                        dfs(board, nr, nc, r2, c2, aMove+1, bMove, false);
                        board[r1][c1] = 1;
                    }
                }
            }
        } else {
            for (int d = 0;d<4;d++) {
                int nr = r2 + dr[d];
                int nc = c2 + dc[d];
                if (!isOutside(N, M, nr, nc)) {
                    if (board[nr][nc] == 1) {
                        board[r2][c2] = 0;
                        dfs(board, r1, c1, nr, nc, aMove, bMove+1, true);
                        board[r2][c2] = 1;
                    }
                }
            }
        }

    }

    static public boolean canMove(int[][] board, int r, int c) {

        int N = board.length;
        int M = board[0].length;
        boolean enable = false;

        for (int d = 0;d<4;d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!isOutside(N,M,nr,nc)) {
                if (board[nr][nc] == 1) {
                    enable = true;
                    break;
                }
            }
        }

        return enable;
    }


    static public boolean isOutside(int N, int M, int r, int c) {
        return r < 0 || r>=N || c<0 || c>=M;
    }

    public static void main(String[] args) {
        int num = solution(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}, new int[]{1, 0}, new int[]{1,2});

        System.out.println(num);
    }
}
