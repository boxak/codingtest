package solutions.implementaition;

import java.util.ArrayList;

public class StringCompress {
    public static int solution(String s) {
        int answer = 0;

        if (s.length() == 1) return 1;

        answer = compressStr(s);

        return answer;
    }

    static int compressStr(String s) {
        int answer = Integer.MAX_VALUE;
        for (int len = 1;len<=s.length()/2;len++) {
            ArrayList<String> list = sliceStr(s,len);
//zzzz
            String temp = list.get(0);
            int cnt = 1;
            StringBuilder sb = new StringBuilder();

            for (int index = 1;index<list.size();index++) {
                if (temp.equals(list.get(index))) {
                    cnt++;
                } else {
                    if (cnt == 1) sb.append(temp);
                    else {
                        sb.append(cnt);
                        sb.append(temp);
                    }
                    temp = list.get(index);
                    cnt = 1;
                }
            }

            if (cnt == 1) sb.append(temp);
            else {
                sb.append(cnt);
                sb.append(temp);
            }

            if (answer > sb.length()) answer = sb.length();
        }
        return answer;
    }

    static ArrayList<String> sliceStr(String s, int len) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0;i*len<s.length();i++) {
            if ((i+1)*len>s.length()) list.add(s.substring(i*len));
            else list.add(s.substring(i*len,(i+1)*len));
        }

        return list;
    }

    public static void main(String[] args) {
        solution("s");
    }
}
