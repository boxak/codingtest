package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Game2048 {
	
	static int N;
	static int[][] map;
	static int[][] cmap;
	static boolean[][] checked;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	static int[] arr;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		cmap = new int[N+1][N+1];
		checked = new boolean[N+1][N+1];
		arr = new int[5];
		
		for (int i = 1;i<=N;i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			for (int j = 1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		
		//dfs(0);
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				cmap[i][j] = map[i][j];
			}
		}
		
		moveUp();
		print();
		
		System.out.println(answer);
		
	}
	
	static void dfs(int x) {
		if (x==5) {
			simulation();
			return;
		}
		
		for (int i = 0;i<4;i++) {
			arr[x] = i;
			dfs(x+1);
		}
		
	}
	
	static void simulation() {

		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				cmap[i][j] = map[i][j];
			}
		}
		
		for (int i = 0;i<5;i++) {
			//print();
			init();
			if (arr[i]==0) moveUp();
			if (arr[i]==1) moveRight();
			if (arr[i]==2) moveDown();
			if (arr[i]==3) moveLeft();
		}
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				if (cmap[i][j]>answer) answer = cmap[i][j];
			}
		}
		
	}
	
	static void init() {
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				checked[i][j] = false;
			}
		}
	}
	
	static void moveUp() {
		for (int i = 2;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				if (cmap[i][j]!=0) {
					boolean isMeet = false;
					int num = cmap[i][j];
					for (int r = i-1;r>=1;r--) {
						if (cmap[r][j]!=0) {
							isMeet = true;
							if (cmap[r][j] == cmap[i][j] && !checked[r][j]) {
								checked[r][j] = true;
								cmap[i][j] = 0;
								cmap[r][j] = num*2;
							} else {
								cmap[i][j] = 0;
								cmap[r+1][j] = num;
							}
							break;
						}
					}
					if(!isMeet) {
						cmap[i][j] = 0;
						cmap[1][j] = num;
					}
				}
			}
		}
	}
	
	static void moveDown() {
		for (int i = N-1;i>=1;i--) {
			for (int j = 1;j<=N;j++) {
				if (cmap[i][j]!=0) {
					boolean isMeet = false;
					int num = cmap[i][j];
					for (int r = i+1;r<=N;r++) {
						if (cmap[r][j]!=0) {
							isMeet = true;
							if (cmap[r][j] == cmap[i][j] && !checked[r][j]) {
								checked[r][j] = true;
								cmap[i][j] = 0;
								cmap[r][j] = num*2;
							} else {
								cmap[i][j] = 0;
								cmap[r-1][j] = num;
							}
							break;
						}
					}
					if(!isMeet) {
						cmap[i][j] = 0;
						cmap[N][j] = num;
					}
				}
			}
		}
	}
	
	static void moveLeft() {
		for (int j = 2;j<=N;j++) {
			for (int i = 1;i<=N;i++) {
				if (cmap[i][j]!=0) {
					boolean isMeet = false;
					int num = cmap[i][j];
					for (int c = j-1;c>=1;c--) {
						if (cmap[i][c]!=0) {
							isMeet = true;
							if (cmap[i][c] == cmap[i][j] && !checked[i][c]) {
								checked[i][c] = true;
								cmap[i][j] = 0;
								cmap[i][c] = num*2;
							} else {
								cmap[i][j] = 0;
								cmap[i][c+1] = num;
							}
							break;
						}
					}
					if(!isMeet) {
						cmap[i][j] = 0;
						cmap[i][1] = num;
					}
				}
			}
		}
	}
	
	static void moveRight() {
		for (int j = N-1;j>=1;j--) {
			for (int i = 1;i<=N;i++) {
				if (cmap[i][j]!=0) {
					boolean isMeet = false;
					int num = cmap[i][j];
					for (int c = j+1;c<=N;c++) {
						if (cmap[i][c]!=0) {
							isMeet = true;
							if (cmap[i][c] == cmap[i][j] && !checked[i][c]) {
								checked[i][c] = true;
								cmap[i][j] = 0;
								cmap[i][c] = num*2;
							} else {
								cmap[i][j] = 0;
								cmap[i][c-1] = num;
							}
							break;
						}
					}
					if(!isMeet) {
						cmap[i][j] = 0;
						cmap[i][N] = num;
					}
				}
			}
		}
	}
	
	static void print() {
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				System.out.printf("%d ", cmap[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
