package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BuildAirstrip {

	static int N,X;
	static int[][] map;
	static int[][] check;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TestCnt = Integer.parseInt(br.readLine());
		int[] answers = new int[TestCnt+1];
		
		for (int test = 1;test<=TestCnt;test++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			answer = 0;
			
			map = new int[N+1][N+1];
			check = new int[N+1][N+1];
			
			for (int i = 1;i<=N;i++) {
				str = br.readLine();
				st = new StringTokenizer(str," ");
				for (int j = 1;j<=N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			makeAirstrip(0);
			checkPossible(0);
			makeAirstrip(1);
			checkPossible(1);
			
			answers[test] = answer;
			
		}
		
		for (int i = 1;i<=TestCnt;i++) System.out.println("#"+i+" "+answers[i]);
		
	}
	
	static void makeAirstrip(int gubun) {
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				check[i][j] = 0;
			}
		}
		
		int row1 = 0;
		int col1 = 0;
		int row2 = 0;
		int col2 = 0;
		int row3 = 0;
		int col3 = 0;
		
		for (int i = 1;i<=N;i++) {
			for (int j = 2;j<=N-1;j++) {
				for (int k = -1;k<=1;k+=2) {
					
					if (gubun==0) {
						row1 = i;
						col1 = j;
						
						row2 = i;
						col2 = j+k;
					} else {
						row1 = j;
						col1 = i;
						
						row2 = j+k;
						col2 = i;
					}
					
					if (map[row1][col1]-map[row2][col2]==1) {
						for (int f = 1;f<=X;f++) {
							
							if (gubun==0) {
								row3 = i;
								col3 = j+f*k;
							} else {
								row3 = j+f*k;
								col3 = i;
							}
							if (row3<1 || row3>N || col3<1 || col3>N) break;
							
							check[row3][col3]++;
						}
						
					}
				}
			}
		}
		
	}
	
	static void checkPossible(int gubun) {
		
		int row1 = 0;
		int col1 = 0;
		int row2 = 0;
		int col2 = 0;
		
		for (int i = 1;i<=N;i++) {
			boolean flag = true;
			Roop1 : for (int j = 2;j<=N-1;j++) {
				for (int k = -1;k<=1;k+=2) {
					
					if (gubun==0) {
						row1 = i;
						col1 = j;
						
						row2 = i;
						col2 = j+k;
					} else {
						row1 = j;
						col1 = i;
						
						row2 = j+k;
						col2 = i;
					}
					
					if (map[row1][col1]-map[row2][col2]>1) {
						flag = false;
						break Roop1;
					}
					if (map[row1][col1]-map[row2][col2]==1 && check[row2][col2]==0) {
						flag = false;
						break Roop1;
					}
					if (map[row1][col1]-map[row2][col2]==1 && (j+X*k<1 || j+X*k>N)) {
						flag = false;
						break Roop1;
					}
				}
			}
			Roop2 : for (int j = 1;j<=N;j++) {
				
				if (gubun==0) {
					row1 = i;
					col1 = j;
				} else {
					row1 = j;
					col1 = i;
				}
				
				if (check[row1][col1]>1) {
					flag = false;
					break Roop2;
				}
			}
			if (flag) answer++;
		}
	}

}
