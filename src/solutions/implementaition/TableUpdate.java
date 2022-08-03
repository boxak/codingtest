package solutions.implementaition;

import java.util.ArrayList;
import java.util.Stack;

public class TableUpdate {

    class Node {
        Node next;
        Node prev;
        int data;
        Node(int data) {
            this.next = null;
            this.prev = null;
            this.data = data;
        }
    }

    public String solution(int n, int k, String[] cmds) {

        String answer = "";

        ArrayList<Node> nodes = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        for (int i = 0;i<n;i++) {
            nodes.add(new Node(i));
        }

        for (int i = 0;i<n-1;i++) {
            nodes.get(i).next = nodes.get(i+1);
            nodes.get(i+1).prev = nodes.get(i);
        }

        Node curNode = nodes.get(k);

        for (String cmd : cmds) {
            String[] split = cmd.split(" ");

            if ("U".equals(split[0])) {
                int cnt = Integer.parseInt(split[1]);
                curNode = up(curNode, cnt);
            } else if ("D".equals(split[0])) {
                int cnt = Integer.parseInt(split[1]);
                curNode = down(curNode, cnt);
            } else if ("C".equals(split[0])) {
                stack.add(curNode);
                if (curNode.prev == null) {
                    curNode = curNode.next;
                    curNode.prev = null;
                } else if (curNode.next == null) {
                    curNode = curNode.prev;
                    curNode.next = null;
                } else {
                    Node prev = curNode.prev;
                    Node next = curNode.next;

                    prev.next = next;
                    next.prev = prev;
                    curNode = next;
                }
            } else {
                Node deletedNode = stack.pop();

                Node prev = deletedNode.prev;
                Node next = deletedNode.next;

                if (prev != null) {
                    prev.next = deletedNode;
                }

                if (next != null) {
                    next.prev = deletedNode;
                }
            }

        }

        boolean[] check = new boolean[n];

        while(!stack.isEmpty()) {
            Node node = stack.pop();
            int idx = node.data;
            check[idx] = true;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0;i<n;i++) {
            if (!check[i]) {
                sb.append('O');
            } else {
                sb.append('X');
            }
        }

        answer = sb.toString();

        return answer;
    }

    Node up(Node curNode, int cnt) {
        Node node = curNode;

        for (int i = 0;i<cnt && node!=null;i++) {
            node = node.prev;
        }

        return node;
    }

    Node down(Node curNode, int cnt) {
        Node node = curNode;

        for (int i = 0;i<cnt && node!=null;i++) {
            node = node.next;
        }

        return node;
    }
}
