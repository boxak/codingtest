package solutions.implementaition;

import java.util.ArrayList;
import java.util.Stack;

public class MaximizeFormula {

  static String[] arr = new String[3];
  static boolean[] checked = new boolean[3];
  static int maxValue = Integer.MIN_VALUE;

  public static long solution(String expression) {
    long answer = 0;

    String[] numbers = expression.split("(\\*|\\+|-)");

    ArrayList<String> operList = new ArrayList<>();
    ArrayList<String> operKind = new ArrayList<>();

    for (int i = 0;i<expression.length();i++) {
      char c = expression.charAt(i);
      if (c=='-' || c=='*' || c=='+') {
        operList.add(String.valueOf(c));
        if (!operKind.contains(String.valueOf(c))) {
          operKind.add(String.valueOf(c));
        }
      }
    }

    dfs(numbers,operList,operKind,0);

    return answer;
  }

  public static void dfs(String[] numbers,ArrayList<String> operList,ArrayList<String> operKind, int inx) {
    if (inx == operKind.size()) {
      calculate(numbers,operList,operKind.size());
      return;
    }
    for (int i = 0;i<operKind.size();i++) {
      if (!checked[i]) {
        checked[i] = true;
        arr[inx] = operKind.get(i);
        dfs(numbers,operList,operKind,inx+1);
        checked[i] = false;
      }
    }
  }

  public static void calculate(String[] numbers, ArrayList<String> operList, int size) {
    Stack<String> stack = new Stack<>();
    for (int i = 0;i<operList.size();i++) {
      stack.add(numbers[i]);
      stack.add(operList.get(i));
    }
    stack.add(numbers[numbers.length - 1]);

  }

  public static void main(String[] args) {
    solution("100-200*300-500+20");
  }
}
