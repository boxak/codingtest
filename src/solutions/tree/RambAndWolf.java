package solutions.tree;

public class RambAndWolf {

    static int[] info;
    static Node[] tree;
    static int max = 0;
    static int curNode = 0;
    static int curLamb = 1;
    static int curWolf = 0;
    static int[] wolfCnts;
    static int[] lambCnts;
    static boolean[] visited;

    static class Node {
        int parent = -1;
        int left = -1;
        int right = -1;
    }

    public static int solution(int[] a, int[][] b) {
        int answer = 0;

        info = a.clone();
        tree = new Node[info.length];
        visited = new boolean[info.length];
        lambCnts = new int[info.length];
        wolfCnts = new int[info.length];

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

        for (int i = 0;i<info.length;i++) {
            if (info[i] == 0) {
                lambCnts[i] = 1;
            }
        }

        visit(0);

        visited[0] = true;

        for (int i = 0;i<info.length;i++) {
            System.out.println(i + " - " + " lamb : " + lambCnts[i] + " / wolf : " + wolfCnts[i]);
        }

        while(!isFinished()) {
            int left = tree[curNode].left;
            int right = tree[curNode].right;

            //어떤 노드의 왼쪽 자식이 방문한적 없고, 양이라면 무조건 방문
            if (left != -1 && info[left] == 0 && !visited[left]) {
                curLamb++;
                visited[left] = true;
                curNode = left;
                continue;
            }

            //어떤 노드의 오른쪽 자식이 방문한적 없고, 양이라면 무조건 방문
            if (right != -1 && info[right] == 0 && !visited[right]) {
                curLamb++;
                visited[right] = true;
                curNode = right;
                continue;
            }

            //
            if (left != -1 && info[left] == 1 && !visited[left]) {

            }

        }

        return answer;
    }

    public static boolean isFinished() {
        boolean flag = true;
        for (int i = 0; i< wolfCnts.length; i++) {
            if (info[i] == 0 && wolfCnts[i] < curLamb) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    public static void visit(int node) {
        int left = tree[node].left;
        int right = tree[node].right;
        if (left != -1) {
            wolfCnts[left] = info[node] == 0 ? wolfCnts[node] : wolfCnts[node] + 1;
            visit(left);
        }

        if (right != -1) {
            wolfCnts[right] = info[node] == 0 ? wolfCnts[node] : wolfCnts[node] + 1;
            visit(right);
        }

        System.out.println(node);

        if (left != -1) {
            lambCnts[node] += lambCnts[left];
        }

        if (right != -1) {
            lambCnts[node] += lambCnts[right];
        }
    }


    public static void main(String[] args) {
        int answer = solution(new int[]{0,0,1,1,1,0,1,0,1,0,1,1},
                new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}});

        System.out.println("answer : " + answer);
    }
}
