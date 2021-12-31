package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class MakeBridge2 {
	static int N,M;
	static int[][] map;
	static int answer;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	static ArrayList<Pair> vector;
	static boolean enable;
	static boolean[][] visited;
	static int[][] numberMap;
	static int[] parents;
	static int cnt;
	
	static class Pair implements Comparable<Pair>{
		int v1;
		int v2;
		int cost;
		Pair(int a,int b,int c) {
			v1 = a;
			v2 = b;
			cost = c;
		}
		
		@Override
		public boolean equals(Object obj) {
			return (this.v1==((Pair)obj).v1 && this.v2==((Pair)obj).v2) || (this.v1==((Pair)obj).v2 && this.v2==((Pair)obj).v1);
		}
		
		@Override
		public int compareTo(Pair pair) {
			return this.cost - pair.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		numberMap = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		answer = 0;
		enable = false;
		vector = new ArrayList<>();
		
		for (int i = 1;i<=N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cnt = 0;
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=M;j++) {
				if (!visited[i][j] && map[i][j]==1) {
					cnt++;
					checkNumber(i,j,cnt);
				}
			}
		}
		
		parents = new int[cnt+1];
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=M;j++) {
				if (numberMap[i][j]!=0) {
					for (int d = 0;d<4;d++) {
						drawLine(i,j,numberMap[i][j],d);
					}
				}
			}
		}
		
		kruskal();
		
		System.out.println(enable ? answer : -1);
		
	}
	
	static void kruskal() {
		for (int i = 1;i<=cnt;i++) parents[i] = i;
		
		Collections.sort(vector);
		
		int len = vector.size();
		int count = 0;
		
		for (int i = 0;i<len;i++) {
			int v1 = vector.get(i).v1;
			int v2 = vector.get(i).v2;
			int cost = vector.get(i).cost;
			
			if (findSet(v1)==findSet(v2)) continue;
			
			parents[findSet(v1)] = findSet(v2);
			answer+=cost;
			if (++count>=cnt-1) {
				enable = true;
				break;
			}
			
		}
		
	}
	
	static int findSet(int x) {
		if (parents[x]==x) return x;
		else return findSet(parents[x]);
	}
	
	static void drawLine(int r,int c,int num,int d) {
		for (int go = 1;;go++) {
			int nr = r+go*dr[d];
			int nc = c+go*dc[d];
			if (isOut(nr,nc)) break;
			if (map[nr][nc]==1) {
				int nextNum = numberMap[nr][nc];
				if (go-1>=2  && num!=nextNum) {
					if (!vector.contains(new Pair(num,nextNum,0))) {
						vector.add(new Pair(num,nextNum,go-1));
					} else {
						int index = vector.indexOf(new Pair(num,nextNum,0));
						int cost1 = vector.get(index).cost;
						if (go-1 < cost1) {
							vector.set(index, new Pair(num,nextNum,go-1));
						}
					}
				}
				break;
			}
		}
	}
	
	static void checkNumber(int r,int c,int num) {
		visited[r][c] = true;
		numberMap[r][c] = num;
		
		for (int d = 0;d<4;d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if (isOut(nr,nc)) continue;
			if (!visited[nr][nc] && map[nr][nc]==1) {
				checkNumber(nr,nc,num);
			}
		}
		
	}
	
	static boolean isOut(int r,int c) {
		return r<1 || r>N || c<1 || c>M;
	}
	
}
