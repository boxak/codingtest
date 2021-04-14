package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MST {

    static class Pair implements Comparable<Pair>{
        int v1;
        int v2;
        int cost;
        Pair(int x,int y,int z) {
            v1 = x;
            v2 = y;
            cost = z;
        }

        public int compareTo(Pair pair) {
            return this.cost - pair.cost;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<Pair> list = new ArrayList<>();
        int answer = 0;

        for (int i = 0;i<E;i++) {
            str = br.readLine();
            st = new StringTokenizer(str," ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.add(new Pair(v1,v2,cost));
        }

        Collections.sort(list);

        answer = kruskal(list,V,E);

        System.out.println(answer);
    }

    static int kruskal(ArrayList<Pair> list, int V,int E) {
        parent = new int[V+1];
        for (int i = 1;i<=V;i++) parent[i] = i;
        int sum = 0;
        int select = 0;
        for (int i = 0;i<E;i++) {
            int v1 = list.get(i).v1;
            int v2 = list.get(i).v2;
            int cost = list.get(i).cost;
            if (findSet(v1)==findSet(v2)) continue;
            parent[findSet(v1)] = findSet(v2);
            sum+=cost;
            if (++select>=V-1) break;
        }
        return sum;
    }

    static int findSet(int x) {
        if (parent[x] == x) return x;
        return findSet(parent[x]);
    }
}
