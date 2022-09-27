package solutions.implementaition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kakao3 {
//d l r u

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";

        int rowD = Math.abs(x-r);
        int colD = Math.abs(y-c);

        if (rowD + colD > k) return "impossible";
        if ((k - rowD - colD)%2==1) return "impossible";

        int rowMoveCnt = 0;
        int colMoveCnt = 0;

        while(rowMoveCnt + colMoveCnt < k) {
            if (downMovable(rowMoveCnt, colMoveCnt, n, k, x, r, y, c)) {
                rowMoveCnt++;
                x++;
                answer+="d";
            } else if (leftMovable(rowMoveCnt, colMoveCnt, m, k, y, c, x, r)) {
                colMoveCnt++;
                y--;
                answer+="l";
            } else if (rightMovable(rowMoveCnt, colMoveCnt, m, k, y, c, x, r)) {
                colMoveCnt++;
                y++;
                answer+="r";
            } else if (upMovable(rowMoveCnt, colMoveCnt, n, k, x, r, y, c)) {
                rowMoveCnt++;
                x--;
                answer+="u";
            }
        }

        return answer;
    }

    static boolean downMovable(int rowMoveCnt, int colMoveCnt, int n, int k, int x, int r, int y, int c) {
        if (rowMoveCnt + colMoveCnt >= k) return false;
        if (r > x) return true;

        int nextX = x + 1;
        if (nextX > n) return false;

        int dist = Math.abs(nextX-r) + Math.abs(y-c);

        if (rowMoveCnt + colMoveCnt + dist <= k) return true;
        else return false;
    }

    static boolean leftMovable(int rowMoveCnt, int colMoveCnt, int m, int k, int y, int c, int x, int r) {
        if (rowMoveCnt + colMoveCnt >= k) return false;
        if (c < y) return true;

        int nextY = y - 1;
        if (nextY < 1) return false;

        int dist = Math.abs(nextY-c) + Math.abs(x-r);
        if (rowMoveCnt + colMoveCnt + dist <= k) return true;
        else return false;
    }

    static boolean rightMovable(int rowMoveCnt, int colMoveCnt, int m, int k, int y, int c, int x, int r) {
        if (rowMoveCnt + colMoveCnt >= k) return false;
        if (c > y) return true;

        int nextY = y + 1;
        if (nextY > m) return false;

        int dist = Math.abs(nextY-c) + Math.abs(x-r);
        if (rowMoveCnt + colMoveCnt + dist <= k) return true;
        else return false;
    }

    static boolean upMovable(int rowMoveCnt, int colMoveCnt, int n, int k, int x, int r,int y, int c) {
        if (rowMoveCnt + colMoveCnt >= k) return false;
        if (r < x) return true;

        int nextX = x - 1;
        if (nextX < 1) return false;

        int dist = Math.abs(nextX - r) + Math.abs(y-c);

        if (rowMoveCnt + colMoveCnt + dist <= k) return true;
        else return false;
    }

    public static void main(String[] args) {
        String a = solution(6, 7, 2, 3, 4, 5, 12);
        System.out.println(a);

//        StringBuilder sb = new StringBuilder();
//        sb.append("abcde");
//        sb.delete(3,4);
//        System.out.println(sb.toString());
    }
}
