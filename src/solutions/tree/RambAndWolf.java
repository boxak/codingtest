package solutions.tree;

import java.util.ArrayList;

public class RambAndWolf {

    static int[] info;
    static Node[] tree;
    static int maxSheep;

    static class Node {
        int parent = -1;
        int left = -1;
        int right = -1;
    }

    public static int solution(int[] a, int[][] b) {
        int answer = 0;

        info = a.clone();
        tree = new Node[info.length];

        for (int i = 0;i<info.length;i++) {
            tree[i] = new Node();
        }

        for (int i = 0;i<b.length;i++) {
            int parent = b[i][0];
            int child = b[i][1];

            if (tree[parent].left == -1) {
                tree[parent].left = child;
            } else {
                tree[parent].right = child;
            }

            tree[child].parent = parent;
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        dfs(0, 0, 0, list);

        answer = maxSheep;

        return answer;
    }

    public static void dfs(int node, int sheep, int wolf, ArrayList<Integer> nextNodes) {
        System.out.println(node+" "+sheep+" "+wolf);
        if (info[node] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if (wolf >= sheep) return;

        maxSheep = Math.max(sheep, maxSheep);

        int right = tree[node].right;
        int left = tree[node].left;

        if (right != -1) {
            nextNodes.add(right);
        }

        if (left != -1) {
            nextNodes.add(left);
        }

        nextNodes.remove(Integer.valueOf(node));

        for (int next : nextNodes) {
            dfs(next, sheep, wolf, (ArrayList<Integer>) nextNodes.clone());
        }
    }

    public static void main(String[] args) {
        int answer = solution(new int[]{0,0,1,1,1,0,1,0,1,0,1,1},
                new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}});

        System.out.println("answer : " + answer);
    }
}
