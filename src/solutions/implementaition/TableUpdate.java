package solutions.implementaition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class TableUpdate {

    static class Pair {
        int num;
        int pos;
        Pair(int num, int pos) {
            this.num = num;
            this.pos = pos;
        }
    }

    static public String solution(int n, int k, String[] cmds) {
        String answer = "";

        LinkedList<Integer> list = new LinkedList<>();
        Stack<Pair> stack = new Stack<>();

        for (int i = 0;i<n;i++) {
            list.add(i);
        }

        ListIterator<Integer> iterator = list.listIterator();
        int cnt = 0;

        while(iterator.hasNext() && cnt<k) {
            iterator.next();
            cnt++;
        }

        for (String cmd : cmds) {
            String[] split = cmd.split(" ");
            if ("U".equals(split[0])) {
                int upCnt = Integer.parseInt(split[1]);
                up(iterator, upCnt);
            } else if ("D".equals(split[0])) {
                int downCnt = Integer.parseInt(split[1]);
                down(iterator, downCnt);
            } else if ("C".equals(split[0])) {
                int idx = 0;
                int num = 0;
                if (iterator.hasNext()) {
                    idx = iterator.nextIndex();
                    num = iterator.next();
                }
                iterator.remove();
                stack.add(new Pair(num, idx));
                if (idx >= list.size()) {
                    up(iterator, 1);
                }
            } else {
                Pair pair = stack.peek();
                stack.pop();
                int num = pair.num;
                int pos = pair.pos;
                int curIdx = iterator.nextIndex();

                if (pos > curIdx) {
                    down(iterator, pos - curIdx);
                    iterator.add(num);
                    up(iterator, pos - curIdx + 1);
                } else {
                    up(iterator, curIdx - pos);
                    iterator.add(num);
                    down(iterator, curIdx - pos);
                }
            }
        }

        iterator = list.listIterator();
        boolean[] arr = new boolean[n];

        while(iterator.hasNext()) {
            int pos = iterator.next();
            arr[pos] = true;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0;i<n;i++) {
            if (arr[i]) {
                sb.append('O');
            } else {
                sb.append('X');
            }
        }

        answer = sb.toString();

        return answer;
    }

    static void up(ListIterator<Integer> iterator, int cnt) {
        int a = 0;
        while(iterator.hasPrevious() && a < cnt) {
            iterator.previous();
            a++;
        }
    }

    static void down(ListIterator<Integer> iterator, int cnt) {
        int a = 0;
        while(iterator.hasNext() && a < cnt) {
            iterator.next();
            a++;
        }
    }

    public static void main(String[] args) {
        String str = solution(8,2,new String[]{
                "D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"
        });

        System.out.println(str);
    }
}
