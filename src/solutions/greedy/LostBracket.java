package solutions.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LostBracket {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String formula = br.readLine();

        String[] numberStr = formula.split("-|\\+");
        String[] actions = formula.split("[0-9]{1,}");
        int answer = 0;
        int length = numberStr.length;
        boolean flag = false;

        if (actions.length>0) {

            for (int i = 0; i < length; i++) {
                if (!flag) {
                    if ("-".equals(actions[i])) {
                        flag = true;
                        answer -= Integer.parseInt(numberStr[i]);
                    } else {
                        answer += Integer.parseInt(numberStr[i]);
                    }
                } else {
                    answer -= Integer.parseInt(numberStr[i]);
                }
            }
        } else {
            answer = Integer.parseInt(numberStr[0]);
        }
        System.out.println(answer);
    }
}
