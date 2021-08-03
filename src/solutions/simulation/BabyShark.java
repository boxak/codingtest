package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BabyShark {
	static class Shark {
		int r,c,size,eatCnt;
		Shark(int r,int c) {
			this.r = r;
			this.c = c;
			size = 2;
			eatCnt = 0;
		}
		
		void moveTo(int r,int c) {
			this.r = r;
			this.c = c;
			eatCnt++;
			if (eatCnt==size) {
				size++;
				eatCnt = 0;
			}
		}
	}
	
	static class Fish implements Comparable<Fish>{
		int r,c,dist;
		Fish(int r,int c,int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Fish fish) {
			if (fish.dist == this.dist) {
				if (fish.r==this.r) {
					return this.c-fish.c;
				} else return this.r - fish.r;
			} else return this.dist-fish.dist;
		}
	}
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	static int answer;
	static Shark shark;
	static ArrayList<Fish> fishes;
	static Queue<int[]> que;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		answer = 0;
		fishes = new ArrayList<>();
		que = new LinkedList<>();
		
		for (int i = 1;i<=N;i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			for (int j = 1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==9) {
					shark = new Shark(i,j);
					map[i][j] = 0;
				}
			}
		}
		
		while(true) {
			fishes.clear();
			boolean continueable = move();
			if (!continueable) break;
		}
		
		System.out.println(answer);
		
	}
	
	static boolean move() {
		searchFish();
		Collections.sort(fishes);
		boolean continueable = false;
		if (fishes.size()>0) {
			Fish fish = fishes.get(0);
			shark.moveTo(fish.r, fish.c);
			map[fish.r][fish.c] = 0;
			answer+=fish.dist;
			continueable = true;
		}
		return continueable;
	}
	
	static void searchFish() {
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				visited[i][j] = false;
			}
		}
		
		que.clear();
		
		que.add(new int[] {shark.r,shark.c,0});
		visited[shark.r][shark.c] = true;
		
		while(!que.isEmpty()) {
			int r = que.peek()[0];
			int c = que.peek()[1];
			int dist = que.peek()[2];
			que.poll();
			for (int d = 0;d<4;d++) {
				int nr = r+dr[d];
				int nc = c+dc[d];
				if (nr<1 || nr>N || nc<1 || nc>N) continue;
				if (!visited[nr][nc] && map[nr][nc]<=shark.size) {
					visited[nr][nc] = true;
					que.add(new int[] {nr, nc, dist+1});
					if (map[nr][nc]<shark.size && map[nr][nc]>0) {
						fishes.add(new Fish(nr,nc,dist+1));
					}
				}
			}
		}
		
	}
}
