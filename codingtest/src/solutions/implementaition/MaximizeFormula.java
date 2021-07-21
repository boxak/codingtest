package solutions.implementaition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaximizeFormula {

    static String[] arr = new String[3];
    static boolean[] checked = new boolean[3];
    static long maxValue = Integer.MIN_VALUE;

    public static long solution(String expression) {
        long answer = 0;

        ArrayList<String> operList = new ArrayList<>();
        ArrayList<String> operKind = new ArrayList<>();
        expression = expression.replaceAll("\\+", "a");
        expression = expression.replaceAll("-", "b");
        expression = expression.replaceAll("\\*", "c");

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == 'a' || c == 'b' || c == 'c') {
                operList.add(String.valueOf(c));
                if (!operKind.contains(String.valueOf(c))) {
                    operKind.add(String.valueOf(c));
                }
            }
        }

        dfs(expression, operList, operKind, 0);
        answer = maxValue;
        return answer;
    }

    public static void dfs(String expression, ArrayList<String> operList, ArrayList<String> operKind, int inx) {
        if (inx == operKind.size()) {
            calculate(expression, operList, operKind.size());
            return;
        }
        for (int i = 0; i < operKind.size(); i++) {
            if (!checked[i]) {
                checked[i] = true;
                arr[inx] = operKind.get(i);
                dfs(expression, operList, operKind, inx + 1);
                checked[i] = false;
            }
        }
    }

    public static void calculate(String expression, ArrayList<String> operList, int size) {
        String s = expression;
        for (int i = 0; i < size; i++) {
            String operation = arr[i];
            while (true) {
                //System.out.println(operation + " : " + s);
                String regex = "-{0,1}[0-9]{1,}" + operation + "-{0,1}[0-9]{1,}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(s);
                if (!matcher.find()) break;
                String tmp = matcher.group();
                String[] twoNum = tmp.split(operation);
                String resultStr = null;
                long result = 0;
                if ("a".equals(operation)) {
                    result = Long.parseLong(twoNum[0]) + Long.parseLong(twoNum[1]);
                } else if ("c".equals(operation)) {
                    result = Long.parseLong(twoNum[0]) * Long.parseLong(twoNum[1]);
                } else {
                    result = Long.parseLong(twoNum[0]) - Long.parseLong(twoNum[1]);
                }
                resultStr = Long.toString(result);
                s = s.replaceFirst(regex, resultStr);
            }
        }
        long finalResult = Math.abs(Long.parseLong(s));
        if (maxValue<finalResult) maxValue = finalResult;
    }

    public static void main(String[] args) {
        long answer = solution("100-200*300-500+20");
        System.out.println(answer);
        /*String s = "100b200c300b500a20";
        Pattern regex = Pattern.compile("-{0,1}[0-9]{1,}c-{0,1}[0-9]{1,}");
        Matcher matcher = regex.matcher(s);
        if (matcher.find()) {
            String tmp = matcher.group();
            String[] twoNum = tmp.split("c");
            int result = Integer.parseInt(twoNum[0]) * Integer.parseInt(twoNum[1]);
            String resultStr = Integer.toString(result);
            s = s.replaceFirst("-{0,1}[0-9]{1,}c-{0,1}[0-9]{1,}", resultStr);
        }
        System.out.println(s);*/

    }
}
