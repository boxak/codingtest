package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DiceYutNori {
	static int[] numbers;
	static int answer;
	static int[] orders;
	static int[] scores;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		numbers = new int[10];
		orders = new int[100];
		scores = new int[100];
		answer = Integer.MIN_VALUE;
		
		for (int i = 0;i<10;i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		makeOrders();
		
		dfs(0,0,0,0,0,0,0);
		
		System.out.println(answer);
		
	}
	
	static void dfs(int x,int nowpos,int p1,int p2,int p3,int p4,int sum) {
		if (x==10) {
			if (sum>answer) answer = sum;
			return;
		}
		
		int np1 = p1;
		int np2 = p2;
		int np3 = p3;
		int np4 = p4;
		
		for (int i = 0;i<numbers[x];i++) {
			if (i == 0) {
				if (np1==5 || np1==10 || np1==15) {
					if (np1==5) np1 = 21;
					if (np1==10) np1 = 25;
					if (np1==15) np1 = 27;
				} else np1 = orders[np1];
				
				if (np2==5 || np2==10 || np2==15) {
					if (np2==5) np2 = 21;
					if (np2==10) np2 = 25;
					if (np2==15) np2 = 27;
				} else np2 = orders[np2];
				
				if (np3==5 || np3==10 || np3==15) {
					if (np3==5) np3 = 21;
					if (np3==10) np3 = 25;
					if (np3==15) np3 = 27;
				} else np3 = orders[np3];
				
				
				if (np4==5 || np4==10 || np4==15) {
					if (np4==5) np4 = 21;
					if (np4==10) np4 = 25;
					if (np4==15) np4 = 27;
				} else np4 = orders[np4];
			} else {
				np1 = orders[np1];
				np2 = orders[np2];
				np3 = orders[np3];
				np4 = orders[np4];
			}
		}
		
		if (np1>=40) np1 = 0;
		if (np2>=40) np2 = 0;
		if (np3>=40) np3 = 0;
		if (np4>=40) np4 = 0;
		
		if (nowpos==0) {
			dfs(x+1,np1,np1,p2,p3,p4,sum+scores[np1]);
			dfs(x+1,np2,p1,np2,p3,p4,sum+scores[np2]);
			dfs(x+1,np3,p1,p2,np3,p4,sum+scores[np3]);
 			dfs(x+1,np4,p1,p2,p3,np4,sum+scores[np4]);
		} else {
			if (nowpos!=p1) dfs(x+1,np1,np1,p2,p3,p4,sum+scores[np1]);
			if (nowpos!=p2) dfs(x+1,np2,p1,np2,p3,p4,sum+scores[np2]);
			if (nowpos!=p3) dfs(x+1,np3,p1,p2,np3,p4,sum+scores[np3]); 
			if (nowpos!=p4) dfs(x+1,np4,p1,p2,p3,np4,sum+scores[np4]);
		}
	}
	
	static void makeOrders() {
		for (int i = 0;i<19;i++) {
			orders[i] = i+1;
			scores[i] = 2*i;
		}
		
		orders[20] = 40;
		scores[20] = 40;
		
		for (int i = 40;i<50;i++) orders[i] = i+1;
		
		orders[21] = 22;
		orders[22] = 23;
		orders[23] = 24;
		
		scores[21] = 13;
		scores[22] = 16;
		scores[23] = 19;
		
		orders[25] = 26;
		orders[26] = 24;
		
		scores[25] = 22;
		scores[26] = 24;
		
		orders[27] = 28;
		orders[28] = 29;
		orders[29] = 24;
		
		scores[27] = 28;
		scores[28] = 27;
		scores[29] = 26;
		
		orders[30] = 31;
		orders[31] = 20;
		
		scores[30] = 30;
		scores[31] = 35;
		
	}
	
}
