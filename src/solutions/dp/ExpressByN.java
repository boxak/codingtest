package solutions.dp;

import java.util.LinkedList;
import java.util.Queue;

public class ExpressByN {

    static class Pair {
        int num;
        int cnt;

        Pair(int x, int y) {
            num = x;
            cnt = y;
        }
    }

    public static int solution(int N, int number) {
        int answer = -1;

        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(0, 0));
        boolean find = false;
        int check[] = new int[100000];

        for (int i = 0;i<100000;i++) check[i] = Integer.MAX_VALUE;

        while (true) {
            int queSize = que.size();
            for (int x = 0;x<queSize;x++) {
                int num = que.peek().num;
                int cnt = que.peek().cnt;
                que.poll();
                StringBuilder sb = new StringBuilder();
                int nn = 0;
                for (int w = 1; w <= 5; w++) {
                    sb.append(N);
                    nn = Integer.parseInt(sb.toString());
                    int nums[] = new int[3];

                    nums[0] = ((long)num + nn >= 100000 ? 0 : num + nn);
                    nums[1] = ((long)num * nn >= 100000 ? 0 : num * nn);
                    nums[2] = num / nn;

                    System.out.println(num+" "+nums[0] + " " +nums[1]+" "+nums[2]);

                    for (int i = 0;i<2;i++) {
                        if (check[nums[i]]>cnt+w) {
                            que.add(new Pair(nums[i],cnt+w));
                            check[nums[i]] = cnt+w;
                        }
                    }
                    if (check[nums[2]]>cnt+w && num%nn == 0) {
                        que.add(new Pair(nums[2],cnt+w));
                        check[nums[2]] = cnt+w;
                    }
                    if (nums[0] == number || nums[1] == number || (num % nn == 0 && nums[2] == number)) {
                        find = true;
                        break;
                    }
                }
                if (find) break;
            }
            if (find) break;
        }

        if (check[number] > 8) answer = -1;
        else answer = check[number];
        System.out.println("number : "+check[number]);

        return answer;
    }

    public static void main(String args[]) {
        System.out.println(solution(2, 11));
    }
}
