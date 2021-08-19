package solutions.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HomeSecurityService {

	static int N,M;
	static int[][] map;
	static int answer;
	static int houseCnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TestCnt = Integer.parseInt(br.readLine());
		int[] answers = new int[TestCnt+1];
		
		for (int test = 1;test<=TestCnt;test++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N+1][N+1];
			houseCnt = 0;
			answer = 0;
			
			for (int i = 1;i<=N;i++) {
				str = br.readLine();
				st = new StringTokenizer(str," ");
				for (int j = 1;j<=N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j]==1) houseCnt++;
				}
			}
			
			for (int i = 1;i<=N;i++) {
				for (int j = 1;j<=N;j++) {
					for (int k = 1;;k++) {
						int dist = k-1;
						int tempCnt = 0;
						
						for (int r = 1;r<=N;r++) {
							for (int c = 1;c<=N;c++) {
								if (Math.abs(r-i)+Math.abs(c-j)<=dist && map[r][c]==1) {
									tempCnt++;
								}
							}
						}
						
						int revenue = M*tempCnt - k*k-(k-1)*(k-1);
						if (revenue>=0) {
							if (tempCnt>answer) answer = tempCnt;
						}
						
						
						if (tempCnt==houseCnt) break;
					}
				}
			}
			
			answers[test] = answer;
			
		}
		
		for (int i = 1;i<=TestCnt;i++) System.out.println("#"+i+" "+answers[i]);
		
	}
	
}
