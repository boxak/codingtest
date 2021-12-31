package solutions.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class AddBracket {
	static String str;
	static int N;
	static int[] arr;
	static char[] oper;
	static boolean[] isInBracket;
	static int answer;
	static Queue<Integer> que;
	static Queue<Character> operQue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = br.readLine();
		
		if (N==1) {
			System.out.println(str);
			return;
		}
		
		arr = new int[N/2+1];
		oper = new char[N/2];
		isInBracket = new boolean[N/2];
		answer = Integer.MIN_VALUE;
		que = new LinkedList<>();	
		operQue = new LinkedList<>();
		
		for (int i = 0;i<str.length();i+=2) {
			arr[i/2] = str.charAt(i) - '0';
		}
		
		for (int i = 1;i<str.length();i+=2) {
			oper[i/2] = str.charAt(i);
		}
		
		dfs(0);
		
		System.out.println(answer);
		
	}
	
	static void dfs(int x) {
		if (x==arr.length-1) {
			if (check()) {
				int temp = calculate();
				if (temp>answer) answer = temp;
			}
			return;
		}
		
		isInBracket[x] = true;
		dfs(x+1);
		isInBracket[x] = false;
		dfs(x+1);
	}
	
	static boolean check() {
		boolean flag = true;
		for (int i = 1;i<isInBracket.length;i++) {
			if (isInBracket[i-1] && isInBracket[i]) {
				flag = false;
				break;
			}
		}
		
		return flag;
	}
	
	static int calculate() {
		int sum = 0;
		
		que.clear();
		operQue.clear();
		
		for (int i = 0;i<isInBracket.length;i++) {
			if (i>=1 && isInBracket[i-1]) {
				operQue.add(oper[i]);
				continue;
			}
			int temp = arr[i];
			if (isInBracket[i]) {
				temp = getResult(oper[i],arr[i],arr[i+1]);
			} else operQue.add(oper[i]);
			que.add(temp);
		}
		
		if (!isInBracket[isInBracket.length-1]) que.add(arr[arr.length-1]);
		
		sum = que.peek();
		que.poll();
		
		while(!operQue.isEmpty()) {
			int number = que.peek();
			char c = operQue.peek();
			que.poll();
			operQue.poll();
			sum = getResult(c,sum,number);
		}
		
		return sum;
	}
	
	static int getResult(char c,int a,int b) {
		int temp = 0;
		switch(c) {
			case '+' : 
				temp = a+b;
				break;
			case '-' :
				temp = a-b;
				break;
			case '*' :
				temp = a*b;
				break;
		}
		
		return temp;
	}
	
}
