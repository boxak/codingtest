package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DiceYutNori {
	
	static int[] diceNumbers;
	static int[] orders;
	static int[] scores;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		diceNumbers = new int[10];
		
		for (int i = 0;i<10;i++) {
			diceNumbers[i] = Integer.parseInt(st.nextToken());
		}
		
		answer = Integer.MIN_VALUE;
		
		orders = new int[32];
		scores = new int[33];
		
		for (int i = 0;i<=18;i++) {
			orders[i] = i+1;
			scores[i] = 2*i;
		}
		
		orders[19] = 31;
		scores[19] = 38;
		
		orders[20] = 21;
		scores[20] = 13;
		
		orders[21] = 22;
		scores[21] = 16;
		
		orders[22] = 28;
		scores[22] = 19;
		
		orders[23] = 24;
		scores[23] = 22;
		
		orders[24] = 28;
		scores[24] = 24;
		
		orders[25] = 26;
		scores[25] = 28;
		
		orders[26] = 27;
		scores[26] = 27;
		
		orders[27] = 28;
		scores[27] = 26;
		
		orders[28] = 29;
		scores[28] = 25;
		
		orders[29] = 30;
		scores[29] = 30;
		
		orders[30] = 31;
		scores[30] = 35;
		
		orders[31] = 32;
		scores[31] = 40;
		
		
		dfs(0,0,0,0,0,0);
		
		System.out.println(answer);
		
	}
	
	static void dfs(int inx,int p1,int p2,int p3,int p4,int sum) {
		if (inx==10) {
			if (answer<sum) answer = sum;
			return;
		}
		
		int go = diceNumbers[inx];
		
		int np1 = next(p1,go);
		int np2 = next(p2,go);
		int np3 = next(p3,go);
		int np4 = next(p4,go);
		
		if (p1!=32) {
			if (np1==32) dfs(inx+1,np1,p2,p3,p4,sum);
			else if (np1<32 && np1!=p2 && np1!=p3 && np1!=p4) dfs(inx+1,np1,p2,p3,p4,sum+scores[np1]);
		}
		
		if (p2!=32) {
			if (np2==32) dfs(inx+1,p1,np2,p3,p4,sum);
			else if (np2<32 && np2!=p1 && np2!=p3 && np2!=p4) dfs(inx+1,p1,np2,p3,p4,sum+scores[np2]);
		}
		
		if (p3!=32) {
			if (np3==32) dfs(inx+1,p1,p2,np3,p4,sum);
			else if (np3<32 && np3!=p1 && np3!=p2 && np3!=p4) dfs(inx+1,p1,p2,np3,p4,sum+scores[np3]);
		}
		
		if (p4!=32) {
			if (np4==32) dfs(inx+1,p1,p2,p3,np4,sum);
			else if (np4<32 && np4!=p1 && np4!=p2 && np4!=p3) dfs(inx+1,p1,p2,p3,np4,sum+scores[np4]);
		}
		
	}
	
	static int next(int p,int go) {
		for (int i = 1;i<=go;i++) {
			if (p==32) break;
			if (i==1 && (p==5 || p==10 || p==15)) {
				if (p==5) p = 20;
				else if (p==10) p = 23;
				else if (p==15) p = 25;
			} else {
				p = orders[p];
			}
		}
		return p;
	}
	
}
