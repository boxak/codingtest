package solutions.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LadderControl {
	static int N,M,H;
	static boolean[][] connection;
	static boolean isSuccess;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		isSuccess = false;
		answer = Integer.MAX_VALUE;
		
		connection = new boolean[H+1][N];
		
		for (int i = 0;i<M;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			connection[a][b] = true;
		}
		
		bruteForce();
		
		System.out.println(isSuccess ? answer : -1);
		
	}
	
	static void bruteForce() {
		
		if (check()) {
			answer = 0;
			return;
		}
		
		//H : 세로, N : 가로
		for (int r1 = 1;r1<=H;r1++) {
			for (int c1 = 1;c1<N;c1++) {
				if (connection[r1][c1]) continue;
				if (c1>1 && connection[r1][c1-1]) continue;
				if (c1<N-1 && connection[r1][c1+1]) continue;
				connection[r1][c1] = true;
				if (check()) {
					if (answer>1) answer = 1;
				}
				for (int r2 = r1;r2<=H;r2++) {
					for (int c2 = 1;c2<N;c2++) {
						if (r2==r1 && c2<=c1) continue;
						if (connection[r2][c2]) continue;
						if (c2>1 && connection[r2][c2-1]) continue;
						if (c2<N-1 && connection[r2][c2+1]) continue;
						connection[r2][c2] = true;
						if (check()) {
							if (answer>2) answer = 2;
						}
						for (int r3 = r2;r3<=H;r3++) {
							for (int c3 = 1;c3<N;c3++) {
								if (r3==r2 && c3<=c2) continue;
								if (connection[r3][c3]) continue;
								if (c3>1 && connection[r3][c3-1]) continue;
								if (c3<N-1 && connection[r3][c3+1]) continue;
								connection[r3][c3] = true;
								if (check()) {
									if (answer>3) answer = 3; 
								}
								connection[r3][c3] = false;
							}
						}
						connection[r2][c2] = false;
					}
				}
				connection[r1][c1] = false;
			}
		}
	}
	
	static boolean check() {
		boolean flag = true;
		
//		if (connection[4][2] && connection[1][3] && connection[3][4]) {
//			System.out.println("ZZZZ");
//		}
		
		for (int sc = 1;sc<=N;sc++) {
			int c = sc;
			for (int r=1;r<=H;r++) {
				if (c==1) {
					if (connection[r][c]) {
						c++;
					}
				} else if (c==N) {
					if (connection[r][N-1]) {
						c--;
					}
				} else {
					if (connection[r][c-1]) {
						c--;
					}
					else if (connection[r][c]) {
						c++;
					}
				}
			}
			if (c!=sc) {
				flag = false;
				break;
			}
		}
		
		if (flag) isSuccess = true;
		
		return flag;
	}
	
}
