package solutions.bruteforce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CandidateKey {

    static boolean[] arr = new boolean[8];
    static int columnCnt = 0;
    static int cnt = 0;
    static String[][] record;
    static ArrayList<Set<Integer>> checkList = new ArrayList<>();

    public static int solution(String[][] relation) {
        int answer = 0;
        record = relation;
        columnCnt = relation[0].length;
        dfs(0);
        answer = cnt;
        return answer;
    }

    public static void dfs(int inx) {
        if (inx == columnCnt) {
            checkCandidate();
            return;
        }
        arr[inx] = false;
        dfs(inx+1);
        arr[inx] = true;
        dfs(inx+1);
    }

    public static void checkCandidate() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0;i<columnCnt;i++) {
            if (arr[i]) set.add(i);
        }

        for (Set<Integer> tmpSet : checkList) {
            boolean flag = true;
            for (int num : tmpSet) {
                if (!set.contains(num)) {
                    flag = false;
                    break;
                }
            }
            if (flag) return;
        }

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0;i< record.length;i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0;j<columnCnt;j++) {
                if (arr[j]==true) {
                    sb.append(record[i][j] + " ");
                }
            }
            String result = sb.toString().trim();
            if (!list.contains(result)) {
                list.add(result);
            }
        }
        if (list.size() == record.length) {
            Set<Integer> set1 = new HashSet<>();
            for (int i = 0;i<columnCnt;i++) {
                System.out.printf("%d ",arr[i] ? 1 : 0);
                if (arr[i]) {
                    set1.add(i);
                }
            }
            System.out.println();
            checkList.add(set1);
            cnt++;
        }
    }

    public static void main(String args[]) {
        System.out.println(solution(new String[][]{{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}}));
    }
}
