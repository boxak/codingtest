package solutions.implementaition;

import java.util.ArrayList;

public class SacheonCastle {

  static class Pair {
    int r1 = -1;
    int c1 = -1;
    int r2 = -1;
    int c2 = -1;

    Pair() {}

    Pair(int r1,int c1,int r2,int c2) {
      this.r1 = r1;
      this.c1 = c1;
      this.r2 = r2;
      this.c2 = c2;
    }

  }

  static int cntBlock = 0;
  static ArrayList<Character> answerList = new ArrayList<>();
  static Pair[] arr = new Pair[26];

  public static String solution(int m, int n, String[] board) {
    String answer = "";

    for (int i = 0;i<26;i++) arr[i] = new Pair();

    for (int i = 0;i<board.length;i++) {
      for (int j = 0;j<board[i].length();j++) {
        char c = board[i].charAt(j);
        if (c<='Z' && c>='A') {
          int inx = c - 'A';
          if (arr[inx].r1 == -1) {
            arr[inx].r1 = i;
            arr[inx].c1 = j;
          } else {
            arr[inx].r2 = i;
            arr[inx].c2 = j;
          }
          cntBlock++;
        }
      }
    }

    while(true) {
      int state = getBlockNumber(board, arr);
      if (state != -1) {
        removeBlock(state,board);
        answerList.add((char)(state+'A'));
      }
      if (cntBlock == 0) break;
      else if (state == -1) {
        answer = "IMPOSSIBLE";
        break;
      }
    }

    if (cntBlock == 0) {
      StringBuilder sb = new StringBuilder();
      for (char c : answerList) {
        sb.append(c);
      }
      answer = sb.toString();
    }

    return answer;
  }

  static int getBlockNumber(String[] board, Pair[] arr) {
    int inx = -1;
    for (int i = 0;i<26;i++) {
      boolean flag = false;
      if (arr[i].r1 != -1) {
        if (arr[i].r1==arr[i].r2 || arr[i].c1==arr[i].c2) {
          flag = check1(board,arr[i].r1,arr[i].c1,arr[i].r2,arr[i].c2,(char)(i+'A'));
        } else {
          flag = check2(board,arr[i].r1,arr[i].c1,arr[i].r2,arr[i].c2,(char)(i+'A'));
        }
      }
      if (flag) {
        inx = i;
        break;
      }
    }
    return inx;
  }

  static void removeBlock(int state, String[] board) {

    char c = (char)('A' + state);

    int r1 = arr[state].r1;
    int r2 = arr[state].r2;

    board[r1] = board[r1].replaceFirst(String.valueOf(c),".");
    board[r2] = board[r2].replaceFirst(String.valueOf(c),".");

    arr[state].r1 = -1;
    arr[state].c1 = -1;
    arr[state].r2 = -1;
    arr[state].c2 = -1;

    cntBlock-=2;

  }

  static boolean check1(String[] board,int r1,int c1,int r2,int c2,char c) {

    boolean flag = true;

    if (r1 == r2) {
      int big = c1 > c2 ? c1 : c2;
      int small = c1 > c2 ? c2 : c1;
      for (int i = small;i<=big;i++) {
        if (board[r1].charAt(i)==c) continue;
        if (board[r1].charAt(i)!='.') {
          flag = false;
          break;
        }
      }
    }
    else if (c1 == c2) {
      int big = r1 > r2 ? r1 : r2;
      int small = r1 > r2 ? r2 : r1;
      for (int i = small;i<=big;i++) {
        if (board[i].charAt(c1)==c) continue;
        if (board[i].charAt(c1) != '.') {
          flag = false;
          break;
        }
      }
    }
    return flag;
  }

  static boolean check2(String[] board,int r1,int c1,int r2,int c2,char c) {
    boolean flag = false;
    if (check1(board,r2,c2,r1,c2,c) && check1(board,r1,c1,r1,c2,c)) {
      flag = true;
    }
    if (check1(board,r1,c1,r2,c1,c) && check1(board,r2,c1,r2,c2,c)) {
      flag = true;
    }
    return flag;
  }

  public static void main(String[] args) {
    solution(3,3,new String[]{"DBA","C*A","CDB"});
  }
}
