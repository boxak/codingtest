package solutions.implementaition;

import java.util.*;

public class Kakao2 {
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static String[][] table;
    static int[][] mergeState;
    static String[][] initTable;
    static int[][] initMergeState;
    static boolean[][] visited;

    static class Pair {
        int r;
        int c;
        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static String[] solution(String[] commands) {
        String[] answer = {};

        table = new String[51][51];
        mergeState = new int[51][51];
        initTable = new String[51][51];
        initMergeState = new int[51][51];

        int count = 0;

        List<String> resultList = new ArrayList<>();

        for (int i = 1;i<=50;i++) {
            for (int j = 1;j<=50;j++) {
                table[i][j] = "";
                mergeState[i][j] = ++count;
                initTable[i][j] = "";
                initMergeState[i][j] = mergeState[i][j];
            }
        }

        for (int i = 0;i<commands.length;i++) {
            String[] splited = commands[i].split(" ");
            String command = splited[0];
            if ("UPDATE".equals(command)) {
                String s1 = splited[1];
                String s2 = splited[2];

                if (splited.length == 4) {
                    int r = Integer.parseInt(s1);
                    int c = Integer.parseInt(s2);
                    String value = splited[3];

                    bfs(r, c, 0, mergeState[r][c], value, false);
                } else {
                    for (int row = 1;row<=50;row++) {
                        for (int col = 1;col<=50;col++) {
                            if (!visited[row][col] && s1.equals(table[row][col])) {
                                bfs(row, col, 0, mergeState[row][col], s2, false);
                            }
                        }
                    }
                }
            } else if ("MERGE".equals(command)) {
                int r1 = Integer.parseInt(splited[1]);
                int c1 = Integer.parseInt(splited[2]);
                int r2 = Integer.parseInt(splited[3]);
                int c2 = Integer.parseInt(splited[4]);

                String value1 = table[r1][c1];
                String value2 = table[r2][c2];
                String updateStr = "";

                if ("".equals(value1)) updateStr = value2;
                else updateStr = value1;

                bfs(r2, c2, mergeState[r1][c1], mergeState[r2][c2], updateStr, false);
            } else if ("UNMERGE".equals(command)) {
                int r = Integer.parseInt(splited[1]);
                int c = Integer.parseInt(splited[2]);

                bfs(r, c, 0, mergeState[r][c], table[r][c], true);
            } else {
                int r = Integer.parseInt(splited[1]);
                int c = Integer.parseInt(splited[2]);
                resultList.add("".equals(table[r][c]) ? "EMPTY" : table[r][c]);
            }
        }

        answer = new String[resultList.size()];

        for (int i = 0;i<resultList.size();i++) {
            answer[i] = resultList.get(i);
        }

        return answer;
    }

    static void bfs(int sr, int sc, int target, int origin, String update, boolean unmerge) {
        visited = new boolean[51][51];
        Queue<Pair> que = new LinkedList<>();

        visited[sr][sc] = true;
        que.add(new Pair(sr, sc));
        if (target > 0) {
            mergeState[sr][sc] = target;
            table[sr][sc] = update;
        } else if (!unmerge) {
            table[sr][sc] = update;
        } else {
            table[sr][sc] = update;
            mergeState[sr][sc] = initMergeState[sr][sc];
        }

        while(!que.isEmpty()) {
            int r = que.peek().r;
            int c = que.peek().c;
            que.poll();
            for (int d = 0;d<4;d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 1 || nr>50 || nc < 1 || nc > 50) continue;
                if (!visited[nr][nc] && mergeState[nr][nc] == origin) {
                    visited[nr][nc] = true;
                    que.add(new Pair(nr, nc));
                    if (target > 0) {
                        mergeState[nr][nc] = target;
                        table[nr][nc] = update;
                    } else if (!unmerge){
                        table[nr][nc] = update;
                    } else {
                        table[nr][nc] = initTable[nr][nc];
                        mergeState[nr][nc] = initMergeState[nr][nc];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] commands = new String[]{
                "UPDATE 1 1 menu",
                "UPDATE 1 2 category",
                "UPDATE 2 1 bibimbap",
                "UPDATE 2 2 korean",
                "UPDATE 2 3 rice",
                "UPDATE 3 1 ramyeon",
                "UPDATE 3 2 korean",
                "UPDATE 3 3 noodle",
                "UPDATE 3 4 instant",
                "UPDATE 4 1 pasta",
                "UPDATE 4 2 italian",
                "UPDATE 4 3 noodle",
                "MERGE 1 2 1 4",
                "MERGE 1 3 1 4",
                "UPDATE korean hansik",
                "UPDATE 1 3 group",
                "UNMERGE 1 4",
                "PRINT 1 3",
                "PRINT 1 4"
        };

        String[] result = solution(commands);

        for (String s : result) {
            System.out.println(s);
        }

    }
}
