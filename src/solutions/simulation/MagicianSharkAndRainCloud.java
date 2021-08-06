package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MagicianSharkAndRainCloud {
	
	static class Pair {
		int r;
		int c;
		Pair(int x,int y) {
			r = x;
			c = y;
		}
		
		@Override
		public boolean equals(Object obj) {
			return this.r==((Pair)obj).r && this.c==((Pair)obj).c;
		}
	}
	
	static int answer;
	static final int[] dr = {0,0,-1,-1,-1,0,1,1,1};
	static final int[] dc = {0,-1,-1,0,1,1,1,0,-1};
	static int[][] map;
	static int[][] cmap;
	static boolean[][] rainedMap;
	static ArrayList<Pair> clouds;
	static ArrayList<Pair> newClouds;
	static int[] di;
	static int[] si;
	static int N,M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		cmap = new int[N][N];
		rainedMap = new boolean[N][N];
		clouds = new ArrayList<>();
		newClouds = new ArrayList<>();
		di = new int[M];
		si = new int[M];
		answer = 0;
		
		for (int i = 0;i<N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0;i<M;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			di[i] = Integer.parseInt(st.nextToken());
			si[i] = Integer.parseInt(st.nextToken());
		}
		
		clouds.add(new Pair(N-1,0));
		clouds.add(new Pair(N-1,1));
		clouds.add(new Pair(N-2,0));
		clouds.add(new Pair(N-2,1));
		
		for (int time = 0;time<M;time++) {
			simulation(time);
		}
		
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				answer+=map[i][j];
			}
		}
		
		System.out.println(answer);
		
	}
	
	static void simulation(int time) {
		int dir = di[time];
		int speed = si[time];
		
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				rainedMap[i][j] = false;
			}
		}
		
		moveClouds(dir,speed);
		//print("moveCloud");
		rain();
		//print("rain");
		waterCopy();
		//print("waterCopy");
		makeClouds();
		//print("makeClouds");
	}
	
	static void moveClouds(int d,int s) {
		int size = clouds.size();
		for (int i = 0;i<size;i++) {
			Pair pair = clouds.get(i);
			int nr = pair.r + s*dr[d];
			int nc = pair.c + s*dc[d];
			
			if (nr>=0) nr = nr%N;
			else {
				if (nr%N!=0) {
					nr = N - (-nr)%N;
				} else {
					nr = 0;
				}
			}
			
			if (nc>=0) nc = nc%N;
			else {
				if (nc%N!=0) {
					nc = N - (-nc)%N;
				} else {
					nc = 0;
				}
			}
			
			clouds.set(i, new Pair(nr,nc));
		}
	}
	
	static void rain() {
		for (Pair pair : clouds) {
			map[pair.r][pair.c]++;
			rainedMap[pair.r][pair.c] = true;
		}
	}
	
	static void waterCopy() {
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				int r = i;
				int c = j;
				int water = map[r][c];
				if (rainedMap[r][c]) {
					for (int d = 2;d<=8;d+=2) {
						int nr = r+dr[d];
						int nc = c+dc[d];
						if (nr<0 || nr>=N || nc<0 || nc>=N) continue;
						if (map[nr][nc]>0) water++;
					}
				}
				cmap[r][c] = water;
			}
		}
		
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				map[i][j] = cmap[i][j];
			}
		}
		
		clouds.clear();
	}
	
	static void makeClouds() {
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				if (map[i][j]>=2 && !rainedMap[i][j]) {
					map[i][j]-=2;
					clouds.add(new Pair(i,j));
				}
			}
		}
	}
	
	static void print(String str) {
		System.out.println(str);
		for (int i = 0;i<N;i++) {
			for (int j = 0;j<N;j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
