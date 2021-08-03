package solutions.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class StartTaxi {
	
	static class Pair implements Comparable<Pair>{
		int r;
		int c;
		int cost;
		Pair(int r,int c,int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Pair pair) {
			if (this.cost==pair.cost) {
				if (this.r==pair.r) {
					return this.c-pair.c;
				} else return this.r-pair.r;
			} else return this.cost-pair.cost;
		}
	}
	
	static class Guest {
		int sr;
		int sc;
		int er;
		int ec;
		int dist;
		Guest(int sr,int sc,int er,int ec) {
			this.sr = sr;
			this.sc = sc;
			this.er = er;
			this.ec = ec;
		}
		
		public void setDist(int dist) {
			this.dist = dist;
		}
		
		@Override
		public boolean equals(Object obj) {
			return this.sr==((Guest)obj).sr && this.sc==((Guest)obj).sc;
		}
	}
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static Queue<Pair> que;
	static int[][] map;
	static int[][] guestMap;
	static boolean[][] visited;
	static ArrayList<Guest> guestList;
	static ArrayList<Pair> pairList;
	static int N,M,L;
	static int taxiR,taxiC;
	static boolean enable;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		guestMap = new int[N+1][N+1];
		guestList = new ArrayList<>();
		pairList = new ArrayList<>();
		que = new LinkedList<>();
		enable = true;
		
		for (int i = 1;i<=N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		str = br.readLine();
		st = new StringTokenizer(str," ");
		
		taxiR = Integer.parseInt(st.nextToken());
		taxiC = Integer.parseInt(st.nextToken());
		
		for (int i = 0;i<M;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());
			
			guestMap[sr][sc] = i+1;
			guestList.add(new Guest(sr,sc,er,ec));
		}
		
		for (int i = 0;i<M;i++) {
			bfs(i);
			if (!enable) break;
		}
		
		if (!enable) {
			System.out.println(-1);
			return;
		}
		
		for (int i = 0;i<M;i++) {
			bfs();
			if (!enable) break;
		}
		
		if (!enable) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(L);
		
	}
	
	static void bfs(int inx) {
 		int sr = guestList.get(inx).sr;
		int sc = guestList.get(inx).sc;
		int er = guestList.get(inx).er;
		int ec = guestList.get(inx).ec;
		
		init();
		
		visited[sr][sc] = true;
		que.add(new Pair(sr,sc,0));
		boolean find = false;
		
		while(!que.isEmpty()) {
			int r = que.peek().r;
			int c = que.peek().c;
			int cost = que.peek().cost;
			que.poll();
			if (r==er && c==ec) {
				guestList.get(inx).setDist(cost);
				find = true;
				break;
			}
			for (int d = 0;d<4;d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if (isOut(nr,nc)) continue;
				if (!visited[nr][nc] && map[nr][nc]==0) {
					visited[nr][nc] = true;
					que.add(new Pair(nr,nc,cost+1));
				}
			}
		}
		
		enable = find;
	}
	
	static void bfs() {
		init();
		
		pairList.clear();
		
		visited[taxiR][taxiC] = true;
		que.add(new Pair(taxiR,taxiC,0));
		
		while(!que.isEmpty()) {
			int r = que.peek().r;
			int c = que.peek().c;
			int cost = que.peek().cost;
			que.poll();
			if (guestMap[r][c]!=0) {
				pairList.add(new Pair(r,c,cost));
			}
			
			for (int d = 0;d<4;d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if (isOut(nr,nc)) continue;
				if (!visited[nr][nc] && map[nr][nc]!=1 && L>cost+1) {
					visited[nr][nc] = true;
					que.add(new Pair(nr,nc,cost+1));
				}
			}
		}
		
		if (pairList.size()==0) {
			enable = false;
			return;
		}
		
		Collections.sort(pairList);
		int sr = pairList.get(0).r;
		int sc = pairList.get(0).c;
		int cost = pairList.get(0).cost;
		
		int guestInx = guestList.indexOf(new Guest(sr,sc,0,0));
		int er = guestList.get(guestInx).er;
		int ec = guestList.get(guestInx).ec;
		int dist = guestList.get(guestInx).dist;
		
		if (L-cost-dist<0) {
			enable = false;
			return;
		}
		
		L-=cost+dist;
		L+=2*dist;
		taxiR = er;
		taxiC = ec;
		
		guestMap[sr][sc] = 0;
		guestList.remove(guestInx);
		
	}
	
	static boolean isOut(int r,int c) {
		return r<1 || r>N || c<1 || c>N;
	}
	
	static void init() {
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				visited[i][j] = false;
			}
		}
		que.clear();
	}
}
