package solutions.bruteforce;

import java.util.ArrayList;

public class FindPrime {

    static boolean primeCheck[] = new boolean[10000000];
    static boolean check[] = new boolean[10000000];
    static boolean[] arr = new boolean[7];
    static boolean[] visited = new boolean[7];
    static String str;
    static int len = 0;
    static int ans = 0;
    static int[] arr2 = new int[7];

    public static int solution(String numbers) {
        int answer = 0;
        findPrime();
        str = numbers;
        len = numbers.length();
        dfs(0);
        answer = ans;
        return answer;
    }

    public static void findPrime() {
        primeCheck[1] = true;
        primeCheck[0] = true;
        for (int i = 2;i<10000000;i++) {
            if (!primeCheck[i]) {
                for (int j = 2; j * i < 10000000; j++) {
                    primeCheck[i*j] = true;
                }
            }
        }
    }

    public static void dfs(int inx) {
        if (inx == len) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0;i<len;i++) {
                if (arr[i]==true) {
                    list.add(str.charAt(i) - '0');
                }
            }
            dfs2(list,0);

            return;
        }
        arr[inx] = false;
        dfs(inx+1);
        arr[inx] = true;
        dfs(inx+1);
    }

    public static void dfs2(ArrayList<Integer> list, int inx) {
        if (list.isEmpty()) return;
        if (inx == list.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0;i<list.size();i++) {
                sb.append(arr2[i]);
            }
            int result = Integer.parseInt(sb.toString());
            if (!primeCheck[result] && !check[result]) {
                System.out.println("result : "+result);
                ans++;
                check[result] = true;
            }
            return;
        }
        for (int i = 0;i<list.size();i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr2[inx] = list.get(i);
                dfs2(list,inx+1);
                visited[i] = false;
            }
        }
    }

    public static void main(String args[]) {
        System.out.println("Answer : "+solution("9238473"));
    }
}
