package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ArrayRotation4 {
	static int N,M,K;
	static int[][] map;
	static int[][] cmap;
	static boolean[] visited;
	static int[] arr;
	static int answer;
	static Pair[] rotInfos;
	
	static class Pair {
		int r,c,s;
		Pair(int x,int y,int z) {
			r = x;
			c = y;
			s = z;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		cmap = new int[N+1][M+1];
		visited = new boolean[K];
		arr = new int[K];
		rotInfos = new Pair[K];
		answer = Integer.MAX_VALUE;
		
		for (int i = 1;i<=N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0;i<K;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			rotInfos[i] = new Pair(r,c,s);
		}
		
		dfs(0);
//		for (int i = 1;i<=N;i++) {
//			for (int j = 1;j<=M;j++) {
//				cmap[i][j] = map[i][j];
//			}
//		}
//		rotate(3,4,2);
//		print();
		
		
		System.out.println(answer);
	}
	
	static void dfs(int x) {
		if (x==K) {
			simulation();
			return;
		}
		
		for (int i = 0;i<K;i++) {
			if (!visited[i]) {
				visited[i] = true;
				arr[x] = i;
				dfs(x+1);
				visited[i] = false;
				arr[x] = 0;
			}
		}
	}
	
	static void simulation() {
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=M;j++) {
				cmap[i][j] = map[i][j];
			}
		}
		
		for (int i = 0;i<K;i++) {
			int num = arr[i];
			int r = rotInfos[num].r;
			int c = rotInfos[num].c;
			int s = rotInfos[num].s;
			
			rotate(r,c,s);
		}
		
		int sum = Integer.MAX_VALUE;
		
		for (int i = 1;i<=N;i++) {
			int rowSum = 0;
			for (int j = 1;j<=M;j++) {
				rowSum+=cmap[i][j];
			}
			if (sum>rowSum) sum = rowSum;
		}
		
		if (sum<answer) answer = sum;
	}
	
	static void rotate(int r,int c,int s) {
		int[][] tempMap = new int[2*s+1][2*s+1];
		int[][] tempCmap = new int[2*s+1][2*s+1];
		int l = 2*s+1;
		
		for (int i = r-s;i<=r+s;i++) {
			for (int j = c-s;j<=c+s;j++) {
				tempMap[i-(r-s)][j-(c-s)] = cmap[i][j];
			}
		}
		
		for (int k = 0;k<s;k++) {
			for (int i = k+1;i<l-k;i++) {
				tempCmap[i-1][k] = tempMap[i][k];
			}
			
			for (int j = k;j<l-k-1;j++) {
				tempCmap[k][j+1] = tempMap[k][j];
			}
			
			for (int i = k;i<l-k-1;i++) {
				tempCmap[i+1][l-k-1] = tempMap[i][l-k-1];
			}
			
			for (int j = k+1;j<l-k;j++) {
				tempCmap[l-k-1][j-1] = tempMap[l-k-1][j];
			}
		}
		
		tempCmap[l/2][l/2] = tempMap[l/2][l/2];
		
		for (int i = r-s;i<=r+s;i++) {
			for (int j = c-s;j<=c+s;j++) {
				cmap[i][j] = tempCmap[i-(r-s)][j-(c-s)];
			}
		}
		
	}
	
	static void print() {
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=M;j++) {
				System.out.printf("%d ", cmap[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	 
}
