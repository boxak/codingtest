package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MonominoDomino {
	static int N;
	static int[][] blockInfos;
	static int[][] map;
	static int score;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		score = 0;
	
		
		blockInfos = new int[N][3];
		
		for (int i = 0;i<N;i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			blockInfos[i][0] = t;
			blockInfos[i][1] = x;
			blockInfos[i][2] = y;
		}

		
		map = new int[10][];
		
		for (int i = 0;i<4;i++) {
			map[i] = new int[10];
		}
		
		for (int i=4;i<10;i++) {
			map[i] = new int[4];
		}
		
		for (int i = 0;i<N;i++) {
			simulation(i);
		}
		
		int blockCnt = 0;
		
		for (int i = 0;i<4;i++) {
			for (int j = 4;j<10;j++) {
				if (map[i][j]==1) blockCnt++;
			}
		}
		
		for (int i = 4;i<10;i++) {
			for (int j = 0;j<4;j++) {
				if (map[i][j]==1) blockCnt++;
			}
		}
		
//		for (int i = 0;i<map.length;i++) {
//			for (int j = 0;j<map[i].length;j++) {
//				System.out.printf("%d ",map[i][j]);
//			}
//			System.out.println();
//		}
		
		System.out.printf("%d\n%d",score,blockCnt);
		
	}
	
	static void simulation(int inx) {
		//x는 행, y가 열
		int t = blockInfos[inx][0];
		int x = blockInfos[inx][1];
		int y = blockInfos[inx][2];
		
		drawInRedBoard(t,x,y);
		
		fallInBlue(t);
		
		drawInRedBoard(t,x,y);
		
		fallInGreen(t);
		
		getScoreInBlue();
		getScoreInGreen();
		
		arrangeBlue();
		arrangeGreen();
		
	}
	
	static void getScoreInBlue() {
		for (int j=9;j>=6;) {
			int tempSum = map[0][j]+map[1][j]+map[2][j]+map[3][j];
			if (tempSum==4) {
				map[0][j] = 0;
				map[1][j] = 0;
				map[2][j] = 0;
				map[3][j] = 0;
				score++;
				
				for (int k=j-1;k>=4;k--) {
					map[0][k+1] = map[0][k];
					map[1][k+1] = map[1][k];
					map[2][k+1] = map[2][k];
					map[3][k+1] = map[3][k];
				}
				
				map[0][4] = 0;
				map[1][4] = 0;
				map[2][4] = 0;
				map[3][4] = 0;
				
				
			} else j--;
		}
		
	}
	
	static void getScoreInGreen() {
		for (int i=9;i>=6;) {
			int tempSum = map[i][0]+map[i][1]+map[i][2]+map[i][3];
			if (tempSum==4) {
				map[i][0] = 0;
				map[i][1] = 0;
				map[i][2] = 0;
				map[i][3] = 0;
				score++;
				for (int k=i-1;k>=4;k--) {
					map[k+1][0] = map[k][0];
					map[k+1][1] = map[k][1];
					map[k+1][2] = map[k][2];
					map[k+1][3] = map[k][3];
				}
				
				map[4][0] = 0;
				map[4][1] = 0;
				map[4][2] = 0;
				map[4][3] = 0;
				
			} else i--;
		}
	}
	
	static void arrangeBlue() {
		int cnt = 0;
		for (int j = 4;j<=5;j++) {
			int tempSum = map[0][j]+map[1][j]+map[2][j]+map[3][j];
			if (tempSum>0) cnt++;
		}
		
		for (int j = 9;j>=9-cnt+1;j--) {
			map[0][j] = 0;
			map[1][j] = 0;
			map[2][j] = 0;
			map[3][j] = 0;
		}
		
		for (int j = 9-cnt;j>=4;j--) {
			map[0][j+cnt] = map[0][j];
			map[1][j+cnt] = map[1][j];
			map[2][j+cnt] = map[2][j];
			map[3][j+cnt] = map[3][j];
		}
		
		for (int j = 4;j<4+cnt;j++) {
			map[0][j] = 0;
			map[1][j] = 0;
			map[2][j] = 0;
			map[3][j] = 0;
		}
		
	}
	
	static void arrangeGreen() {
		int cnt = 0;
		for (int i = 4;i<=5;i++) {
			int tempSum = map[i][0]+map[i][1]+map[i][2]+map[i][3];
			if (tempSum>0) cnt++;
		}
		
		for (int i = 9;i>=9-cnt+1;i--) {
			map[i][0] = 0;
			map[i][1] = 0;
			map[i][2] = 0;
			map[i][3] = 0;
		}
		
		for (int i = 9-cnt;i>=4;i--) {
			map[i+cnt][0] = map[i][0];
			map[i+cnt][1] = map[i][1];
			map[i+cnt][2] = map[i][2];
			map[i+cnt][3] = map[i][3];
		}
		
		for (int i = 4;i<4+cnt;i++) {
			map[i][0] = 0;
			map[i][1] = 0;
			map[i][2] = 0;
			map[i][3] = 0;
		}
	}
	
	static void drawInRedBoard(int t,int x,int y) {
		if (t==1) {
			map[x][y] = 1;
		} else if (t==2) {
			map[x][y] = 1;
			map[x][y+1] = 1;
		} else {
			map[x][y] = 1;
			map[x+1][y] = 1;
		}
	}
	
	static void fallInBlue(int t) {
		if (t!=3) {
			for (int j = 3;j>=0;j--) {
				for (int i = 0;i<4;i++) {
					if (map[i][j]==1) {
						int r = i;
						int c = j;
						map[r][c] = 0;
						boolean flag = false;
						for (int k = 1;k<=10;k++) {
							int nc = c+k;
							if (nc==10) break;
							if (map[r][nc]==1) {
								c = nc-1;
								flag = true;
								break;
							}
						}
						if (!flag) c = 9;
						map[r][c] = 1;
					}
				}
			}
		} else {
			for (int j = 3;j>=0;j--) {
				for (int i = 0;i<3;i++) {
					if (map[i][j]==1 && map[i+1][j]==1) {
						int r1 = i;
						int r2 = i+1;
						int c = j;
						map[r1][c] = 0;
						map[r2][c] = 0;
						boolean flag = false;
						for (int k = 1;k<=10;k++) {
							int nc = c+k;
							if (nc==10) break;
							if (map[r1][nc]==1 || map[r2][nc]==1) {
								c = nc-1;
								flag = true;
								break;
							}
						}
						if (!flag) c = 9;
						map[r1][c] = 1;
						map[r2][c] = 1;
					}
				}
			}
		}
	}
	
	static void fallInGreen(int t) {
		if (t!=2) {
			for (int i = 3;i>=0;i--) {
				for (int j = 0;j<4;j++) {
					if (map[i][j]==1) {
						int r = i;
						int c = j;
						map[r][c] = 0;
						boolean flag = false;
						for (int k = 1;k<=10;k++) {
							int nr = r+k;
							if (nr==10) break;
							if (map[nr][c]==1) {
								r = nr-1;
								flag = true;
								break;
							}
						}
						if (!flag) r = 9;
						map[r][c] = 1;
					}
				}
			}
		} else {
			for (int i = 3;i>=0;i--) {
				for (int j = 0;j<3;j++) {
					if (map[i][j]==1 && map[i][j+1]==1) {
						int r = i;
						int c1 = j;
						int c2 = j+1;
						map[r][c1] = 0;
						map[r][c2] = 0;
						boolean flag = false;
						for (int k = 1;k<=10;k++) {
							int nr = r+k;
							if (nr==10) break;
							if (map[nr][c1]==1 || map[nr][c2]==1) {
								r = nr-1;
								flag = true;
								break;
							}
						}
						if (!flag) r = 9;
						map[r][c1] = 1;
						map[r][c2] = 1;
					}
				}
			}
		}
	}
}
