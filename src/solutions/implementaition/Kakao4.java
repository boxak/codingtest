package solutions.implementaition;

public class Kakao4 {

    static boolean enable = true;

    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        int[] lens = {1,3,7,15,31,63};
//
        for (int index = 0;index< numbers.length;index++) {
            long number = numbers[index];
            String s = Long.toBinaryString(number);
            StringBuffer sb = new StringBuffer(s).reverse();

            int originLen = sb.length();
            int level = 0;
            int root = 0;
            enable = true;
            for (int i = 1;i<lens.length;i++) {
                if (lens[i-1]<originLen && lens[i]>=originLen) {
                    originLen = lens[i];
                    level = i;
                    root = lens[i-1];
                    break;
                }
            }

            while(sb.length() < originLen) {
                sb.append("0");
            }

            dfs(sb.toString(), root);

            answer[index] = enable ? 1 : 0;

        }

        return answer;
    }

    static void dfs(String s, int root) {
        if (s.length()<=1) return;
        if (!enable) return;
        String s1 = s.substring(0, root);
        String s2 = s.substring(root+1);

        if (s.charAt(root) == '0') {
            if (s1.contains("1") || s2.contains("1")) {
                enable  = false;
            }
            return;
        }

        dfs(s1, ((root+1)/2)-1);
        dfs(s2, ((root+1)/2)-1);
    }

    public static void main(String[] args) {
        long a = 1;
        for (int i = 0;i<15;i++) {
            a = a*(long)10;
        }
        int[] result = solution(new long[]{63,111,95});

        for (int num : result) {
            System.out.println(num);
        }

    }
}
