package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AirCleaner {
	static int R,C,T;
	static int[][] map;
	static int[][] cmap;
	static int answer;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int cleaner[];
	static int[] uppers;
	static int[] lowers;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		answer = 0;
		
		map = new int[R+1][C+1];
		cmap = new int[R+1][C+1];
		cleaner = new int[2];
		uppers = new int[400];
		lowers = new int[400];
		
		for (int i = 1;i<=R;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 1;j<=C;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==-1) {
					if (cleaner[0]==0) cleaner[0] = i;
					else cleaner[1] = i;
				}
			}
		}
		
		for (int time = 0;time<T;time++) {
			simulation();
		}
		
		for (int i = 1;i<=R;i++) {
			for (int j = 1;j<=C;j++) {
				if (map[i][j]>0) {
					answer+=map[i][j];
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static void simulation() {
		extendDirt();
		windCirculation();
	}
	
	static void extendDirt() {
		for (int i = 1;i<=R;i++) {
			for (int j = 1;j<=C;j++) {
				cmap[i][j] = 0;
			}
		}
		
		for (int i = 1;i<=R;i++) {
			for (int j = 1;j<=C;j++) { 
				if ((i==cleaner[1] || i==cleaner[0]) && j==1) continue;
				int A = map[i][j];				
				int cnt = getCnt(i,j);
				
				for (int d = 0;d<4;d++) {
					int nr = i+dr[d];
					int nc = j+dc[d];
					if (nr<1 || nr>R || nc<1 || nc>C) continue;
					if (nc==1 && (nr==cleaner[1] || nr==cleaner[0])) continue;
					cmap[nr][nc] += A/5;
				}
				map[i][j] = A - cnt*(A/5);
				
			}
		}
		
		for (int i = 1;i<=R;i++) {
			for (int j = 1;j<=C;j++) {
				map[i][j] += cmap[i][j];
			}
		}
	}
	
	static int getCnt(int i,int j) {
		int cnt = 0;
		for (int d = 0;d<4;d++) {
			int nr = i+dr[d];
			int nc = j+dc[d];
			if (nr<1 || nr>R || nc<1 || nc>C) continue;
			if (nc==1 && (nr==cleaner[1] || nr==cleaner[0])) continue;
			cnt++;
		}
		return cnt;
	}
	
	static void windCirculation() {
		upperCirculation();
		lowerCirculation();
	}
	
	static void upperCirculation() {
		for (int i = 0;i<400;i++) uppers[i] = 0;
		
		int row = cleaner[0];
		int inx1 = 0;
		int inx2 = 0;
		
		uppers[inx1++] = 0;
		
		for (int c = 2;c<=C;c++) {
			uppers[inx1++] = map[row][c];
		}
		
		for (int r = row-1;r>=1;r--) {
			uppers[inx1++] = map[r][C];
		}
		
		for (int c = C-1;c>=1;c--) {
			uppers[inx1++] = map[1][c];
		}
		
		for (int r = 2;r<=row-2;r++) {
			uppers[inx1++] = map[r][1];
		}
		
		
		for (int c = 2;c<=C;c++) {
			map[row][c] = uppers[inx2++];
		}
		
		for (int r = row-1;r>=1;r--) {
			map[r][C] = uppers[inx2++];
		}
		
		for (int c = C-1;c>=1;c--) {
			map[1][c] = uppers[inx2++];
		}
		
		for (int r = 2;r<=row-1;r++) {
			map[r][1] = uppers[inx2++];
		}
		
	}
	
	static void lowerCirculation() {
		for (int i = 0;i<400;i++) lowers[i] = 0;
		
		int row = cleaner[1];
		int inx1 = 0;
		int inx2 = 0;
		
		lowers[inx1++] = 0;
		
		for (int c = 2;c<=C;c++) {
			lowers[inx1++] = map[row][c];
		}
		
		for (int r = row+1;r<=R;r++) {
			lowers[inx1++] = map[r][C];
		}
		
		for (int c = C-1;c>=1;c--) {
			lowers[inx1++] = map[R][c];
		}
		
		for (int r = R-1;r>=row+2;r--) {
			lowers[inx1++] = map[r][1];
		}
		
		
		
		for (int c = 2;c<=C;c++) {
			map[row][c] = lowers[inx2++];
		}
		
		for (int r = row+1;r<=R;r++) {
			map[r][C] = lowers[inx2++];
		}
		
		for (int c = C-1;c>=1;c--) {
			map[R][c] = lowers[inx2++];
		}
		
		for (int r = R-1;r>=row+1;r--) {
			map[r][1] = lowers[inx2++];
		}
	}
}
