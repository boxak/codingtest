package solutions.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HarvestHoney {
	static int N,M,C;
	static int[][] map;
	static int[] arr1,arr2;
	static int answer;
	static int revenue1,revenue2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TestCnt = Integer.parseInt(br.readLine());
		int[] answers = new int[TestCnt+1];
		
		for (int test = 1;test<=TestCnt;test++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[N+1][N+1];
			arr1 = new int[M];
			arr2 = new int[M];
			answer = 0;
			
			for (int i = 1;i<=N;i++) {
				str = br.readLine();
				st = new StringTokenizer(str," ");
				for (int j = 1;j<=N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			harvest();
			
			answers[test] = answer;
			
		}
		
		for (int i = 1;i<=TestCnt;i++) System.out.println("#"+i+" "+answers[i]);
		
	}
	
	static void harvest() {
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N-(M-1);j++) {
				for (int k = j;k<j+M;k++) {
					arr1[k-j] = map[i][k];
				}
				for (int f = i;f<=N;f++) {
					for (int h = 1;h<=N-(M-1);h++) {
						if (f==i && h<j+M) continue;
						for (int g = h;g<h+M;g++) {
							arr2[g-h] = map[f][g];
						}
						revenue1 = 0;
						revenue2 = 0;
						dfs1(0,0,0);
						dfs2(0,0,0);
						if (answer<revenue1+revenue2) answer = revenue1 + revenue2;
					}
				}
			}
		}
	}
	
	static void dfs1(int x,int sum,int revenue) {
		if (x==M) {
			if (revenue1<revenue) revenue1 = revenue;
			return;
		}
		
		dfs1(x+1,sum,revenue);
		if (sum+arr1[x]<=C) dfs1(x+1,sum+arr1[x],revenue+arr1[x]*arr1[x]);
	}
	
	static void dfs2(int x,int sum,int revenue) {
		if (x==M) {
			if (revenue2<revenue) revenue2 = revenue;
			return;
		}
		
		dfs2(x+1,sum,revenue);
		if (sum+arr2[x]<=C) dfs2(x+1,sum+arr2[x],revenue+arr2[x]*arr2[x]);
	}
	
}
