package solutions.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LeaveCompany {
	static int N;
	static int[] times;
	static int[] revenues;
	static boolean[] arr;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		times = new int[N+1];
		revenues = new int[N+1];
		arr = new boolean[N+1];
		
		for (int i = 1;i<=N;i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			times[i] = Integer.parseInt(st.nextToken());
			revenues[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1);
		
		System.out.println(answer);
	}
	
	static void dfs(int x) {
		if (x==N+1) {
			if (isAbleSchedule()) {
				int sum = calculate();
				if (sum > answer) {
					answer = sum;
				}
			}
			return;
		}
		
		arr[x] = true;
		dfs(x+1);
		arr[x] = false;
		dfs(x+1);
	}
	
	static boolean isAbleSchedule() {
		boolean enable = true;
		for (int i = 1;i<=N;i++) {
			if (arr[i]) {
				int period = times[i];
				if (period + i-1>N) {
					enable = false;
					break;
				}
				for (int j = i+1;j<=period+i-1;j++) {
					if (arr[j]) {
						enable = false;
						break;
					}
				}
				if (!enable) break;
			}
		}
		
		return enable;
	}
	
	static int calculate() {
		int sum = 0;
		for (int i = 1;i<=N;i++) {
			if (arr[i]) sum+=revenues[i];
		}
		
		return sum;
	}
}
