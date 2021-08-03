package solutions.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Lab3 {
	static int N,M;
	static final int dr[] = {-1,0,1,0};
	static final int dc[] = {0,1,0,-1};
	static int answer;
	static int[][] map;
	static boolean[][] visited;
	static int[] arr;
	static ArrayList<Pair> virusList;
	static Queue<Pair> que;
	static boolean isSuccess;
	
	static class Pair {
		int r;
		int c;
		Pair(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		arr = new int[M];
		isSuccess = false;
		answer = Integer.MAX_VALUE;
		virusList = new ArrayList<>();
		que = new LinkedList<>();
		
		for (int i = 0;i<N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==2) {
					virusList.add(new Pair(i,j));
				} 
			}
		}
		
		dfs(0,0);
		
		System.out.println(isSuccess ? answer : -1);
	}
	
	static void dfs(int x,int from) {
		if (x==M) {
			
//			for (int i = 0;i<M;i++) {
//				System.out.printf("%d ", arr[i]);
//			}
//			System.out.println();
			
			bfs();
			return;
		}
		if (from>=virusList.size()) return;
		for (int i = from;i<virusList.size();i++) {
			arr[x] = i;
			dfs(x+1,i+1);
			arr[x] = 0;
		}
	}
	
	static void bfs() {
		
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				visited[i][j] = false;
			}
		}
		
		que.clear();
		
		for (int i = 0;i<M;i++) {
			int inx = arr[i];
			int r = virusList.get(inx).r;
			int c = virusList.get(inx).c;
			
			visited[r][c] = true;
			que.add(new Pair(r,c));
		}
		
		int time = 0;
		boolean flag = false;
		
		while(!que.isEmpty()) {
			flag = checkFinish();
			if (flag) break;
			int queSize = que.size();
			time++;
			for (int cnt = 0;cnt<queSize;cnt++) {
				int r = que.peek().r;
				int c = que.peek().c;
				que.poll();
					
				for (int d = 0;d<4;d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
					if (!visited[nr][nc] && map[nr][nc]!=1) {
						visited[nr][nc] = true;
						que.add(new Pair(nr,nc));
					}
				}
			}
		}
		
		if (flag) {
			isSuccess = true;
			if (time < answer) answer = time;
		}
		
	}
	
	static boolean checkFinish() {
		boolean flag = true;
		
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				if (map[i][j]==0 && !visited[i][j]) {
					flag = false;
					break;
				}
			}
			if (!flag) break;
		}
		return flag;
	}
	
}
