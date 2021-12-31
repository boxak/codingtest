package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProtectionFilm {

	static int D,W,K;
	static int[][] map;
	static boolean pass;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TestCnt = Integer.parseInt(br.readLine());
		int[] answers = new int[TestCnt+1];
		
		for (int test = 1;test<=TestCnt;test++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[D][W];
			arr = new int[D];
			pass = false;
			
			for (int i = 0;i<D;i++) {
				str = br.readLine();
				st = new StringTokenizer(str," ");
				for (int j = 0;j<W;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0;i<=D;i++) {
				dfs(0,0,i);
				if (pass) {
					answers[test] = i;
					break;
				}
			}
			
		}
		
		for (int i = 1;i<=TestCnt;i++) System.out.println("#"+i+" "+answers[i]);
		
	}
	
	static void dfs(int x,int cnt,int limit) {
		if (pass) return;
		if (cnt==limit) {
			simulation();
			return;
		}
		if (x==D) return;
		
		arr[x] = 1;
		dfs(x+1,cnt+1,limit);
		arr[x] = 2;
		dfs(x+1,cnt+1,limit);
		arr[x] = 0;
		dfs(x+1,cnt,limit);
		
	}
	
	static void simulation() {
		pass = true;
		for (int c = 0;c<W;c++) {
			boolean flag = false;
			for (int r = 0;r<=D-K;r++) {
				int num = map[r][c];
				if (arr[r]==1 || arr[r]==2) num=arr[r]-1;
				boolean flag2 = true;
				for (int f = r+1;f<r+K;f++) {
					int num2 = map[f][c];
					if (arr[f]==1 || arr[f]==2) num2 = arr[f]-1;
					if (num!=num2) {
						flag2 = false; 
						break;
					}
				}
				if (flag2) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				pass = false;
				break;
			}
		}
	}
	
  
}
