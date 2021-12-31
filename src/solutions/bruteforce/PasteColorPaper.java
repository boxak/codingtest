package solutions.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PasteColorPaper {
	static int[][] map;
	static int[][] check;
	static int answer;
	static boolean enable;
	static int oneCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[11][11];
		check = new int[11][11];
		answer = 25;
		
		for (int i = 1;i<=10;i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			for (int j = 1;j<=10;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j]==1) oneCnt++;
			}
		}
		
		dfs(1,1,0,0,0,0,0,oneCnt);
		
		if (enable) System.out.println(answer);
		else System.out.println(-1);
		
	}
	
	static void dfs(int r,int c,int p1,int p2,int p3,int p4,int p5,int cnt) {		
//		if (rot>=100) {
//			for (int i = 1;i<=10;i++) {
//				for (int j = 1;j<=10;j++) {
//					System.out.printf("%d ", check[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		
		
		if (cnt==0) {
			enable = true;
			int temp = p1+p2+p3+p4+p5;
			if (temp<answer) answer = temp;
			return;
		}
		if (r>10) return;
		
		if(map[r][c]!=1 || check[r][c]==1) return; 
		if (p1<5) {
			check[r][c] = 1;
			dfs(c==10 ? r+1 : r,c==10 ? 1 : c+1,p1+1,p2,p3,p4,p5,cnt-1);
			check[r][c] = 0;
		}
		
		if (r<=9 && c<=9 && p2<5) {
			boolean flag = false;
			for (int j = r;j<=r+1;j++) {
				for (int k = c;k<=c+1;k++) {
					if (map[j][k]==1) {
						check[j][k] = 1;
					} else {
						flag = true;
						break;
					}
				}
				if (flag) break;
			} 
			if (!flag) dfs(c==9 ? r+1 : r,c==9 ? 1 : c+2,p1,p2+1,p3,p4,p5,cnt-4);
			for (int j = r;j<=r+1;j++) {
				for (int k = c;k<=c+1;k++) {
					check[j][k] = 0;
				}
			}
		}
		if (r<=8 && c<=8 && p3<5) {
			boolean flag = false;
			for (int j = r;j<=r+2;j++) {
				for (int k = c;k<=c+2;k++) {
					if (map[j][k]==1) {
						check[j][k] = 1;
					} else {
						flag  = true;
						break;
					}
				}
				if (flag) break;
			}
			if (!flag) dfs(c==8 ? r+1 : r,c==8 ? 1 : c+3,p1,p2,p3+1,p4,p5,cnt-9);
			for (int j = r;j<=r+2;j++) {
				for (int k = c;k<=c+2;k++) {
					check[j][k] = 0;
				}
			}
		}
		if (r<=7 && c<=7 && p4<5) {
			boolean flag = false;
			for (int j = r;j<=r+3;j++) {
				for (int k = c;k<=c+3;k++) {
					if (map[j][k]==1) {
						check[j][k] = 1;
					} else {
						flag  = true;
						break;
					}
				}
			}
			if (!flag) dfs(c==7 ? r+1 : r,c==7 ? 1 : c+4,p1,p2,p3,p4+1,p5,cnt-16);
			for (int j = r;j<=r+3;j++) {
				for (int k = c;k<=c+3;k++) {
					check[j][k] = 0;
				}
			}
		}
		if (r<=6 && c<=6 && p5<5) {
			boolean flag = false;
			for (int j = r;j<=r+4;j++) {
				for (int k = c;k<=c+4;k++) {
					if (map[j][k]==1) {
						check[j][k] = 1;
					} else {
						flag  = true;
						break;
					}
				}
			}
			if (!flag) dfs(c==6 ? r+1 : r,c==6 ? 1 : c+5,p1,p2,p3,p4,p5+1,cnt-25);
			for (int j = r;j<=r+4;j++) {
				for (int k = c;k<=c+4;k++) {
					check[j][k] = 0;
				}
			}
		}
		
		dfs(c==10 ? r+1 : r,c==10 ? 1 : c+1,p1,p2,p3,p4,p5,cnt);
	}
}
