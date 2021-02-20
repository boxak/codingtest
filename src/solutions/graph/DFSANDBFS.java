package solutions.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFSANDBFS {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> edge = new ArrayList<>();

        for (int i = 0;i<=N;i++) {
            edge.add(new ArrayList<>());
        }

        for (int i = 0;i<M;i++) {
            str = br.readLine();
            st = new StringTokenizer(str," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edge.get(a).add(b);
            edge.get(b).add(a);
        }

        for (int i = 1;i<=N;i++) {
            Collections.sort(edge.get(i));
        }

        Stack<Integer> stack = new Stack<>();
        boolean visited1[] = new boolean[N+1];

        stack.add(V);
        visited1[V] = true;
        ArrayList<Integer> visitList1 = new ArrayList<>();
        visitList1.add(V);

        while(!stack.isEmpty()) {
            int node = stack.peek();
            boolean flag = false;

            for (int i = 0;i<edge.get(node).size();i++) {
                int next = edge.get(node).get(i);
                if (!visited1[next]) {
                    visited1[next] = true;
                    stack.add(next);
                    flag = true;
                    visitList1.add(next);
                    break;
                }
            }

            if (!flag) {
                stack.pop();
            }

        }

        Queue<Integer> que = new LinkedList<>();
        boolean visited2[] = new boolean[N+1];

        que.add(V);
        visited2[V] = true;

        ArrayList<Integer> visitList2 = new ArrayList<>();
        visitList2.add(V);

        while(!que.isEmpty()) {
            int node = que.peek();
            que.poll();
            for (int i = 0;i<edge.get(node).size();i++) {
                int next = edge.get(node).get(i);
                if (!visited2[next]) {
                    visited2[next] = true;
                    que.add(next);
                    visitList2.add(next);
                }
            }
        }

        for (int num : visitList1) {
            System.out.printf("%d ",num);
        }
        System.out.println();
        for (int num : visitList2) {
            System.out.printf("%d ",num);
        }

    }
}
