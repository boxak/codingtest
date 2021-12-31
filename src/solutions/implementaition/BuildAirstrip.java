package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BuildAirstrip {

	static int N,X;
	static int[][] map;
	static int[] arr;
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
			arr = new int[N+1];
			
			for (int i = 1;i<=N;i++) {
				str = br.readLine();
				st = new StringTokenizer(str," ");
				for (int j = 1;j<=N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			makeAirstripRow();
			makeAirstripCol();		
			answers[test] = answer;
			
		}
		
		for (int i = 1;i<=TestCnt;i++) System.out.println("#"+i+" "+answers[i]);
		
	}
	
	static void makeAirstripRow() {
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) arr[j] = 0;
			boolean flag = true;
			Roop1 : for (int j = 1;j<=N;j++) {
				for (int k = -1;k<=1;k+=2) {
					if ((j==1 && k==-1) || (j==N && k==1)) continue;
					if (map[i][j]-map[i][j+k]>=2) {
						flag = false;
						break Roop1;
					} else if (map[i][j]-map[i][j+k]==1) {
						for (int h=1;h<=X;h++) {
							if (j+h*k<1 || j+h*k>N) {
								flag = false;
								break Roop1;
							}
							arr[j+h*k]++;
						}
					}
				}
			}
			
			if (flag) {
				for (int j = 1;j<=N;j++) {
					if (arr[j]>=2) {
						flag = false;
						break;
					}
				}
			}
			if (flag) answer++;
		}
	} 
	
	static void makeAirstripCol() {
		for (int j = 1;j<=N;j++) {
			for (int i = 1;i<=N;i++) arr[i] = 0;
			boolean flag = true;
			Roop1 : for (int i = 1;i<=N;i++) {
				for (int k = -1;k<=1;k+=2) {
					if ((i==1 && k==-1) || (i==N && k==1)) continue;
					if (map[i][j]-map[i+k][j]>=2) {
						flag = false;
						break Roop1;
					} else if(map[i][j]-map[i+k][j]==1) {
						for (int h=1;h<=X;h++) {
							if (i+h*k<1 || i+h*k>N) {
								flag = false;
								break Roop1;
							}
							arr[i+h*k]++;
						}
					
					}
				}
			}
			if (flag) {
				for (int i = 1;i<=N;i++) {
					if (arr[i]>=2) {
						flag = false;
						break;
					}
				}
			}
			if (flag) answer++;
		}
	}

}
