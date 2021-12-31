package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CarCenter {
	
	static int N,M,K,A,B;
	static int[] time1,time2;
	static Guest[] changu1,changu2;
	static int[] arrives;
	static boolean[] checked;
	static Queue<Integer> waitQue1,waitQue2;
	static boolean[] usedA,usedB;
	static int answer;
	static int finished;
	
	static class Guest {
		int num;
		int time;
		Guest(int a,int b) {
			num = a;
			time = b;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TestCnt = Integer.parseInt(br.readLine());
		int[] answers = new int[TestCnt+1];
		
		for (int test = 1;test<=TestCnt;test++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str," ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			time1 = new int[N+1];
			time2 = new int[M+1];
			arrives = new int[K+1];
			changu1 = new Guest[N+1];
			changu2 = new Guest[M+1];
			usedA = new boolean[K+1];
			usedB = new boolean[K+1];
			checked = new boolean[K+1];
			waitQue1 = new LinkedList<>();
			waitQue2 = new LinkedList<>();
			answer = 0;
			finished = 0;
			
			for (int i = 1;i<=N;i++) changu1[i] = new Guest(0,0);
			for (int i = 1;i<=M;i++) changu2[i] = new Guest(0,0);
			
			str = br.readLine();
			st = new StringTokenizer(str," ");
			
			for (int i = 1;i<=N;i++) time1[i] = Integer.parseInt(st.nextToken());
			
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int i = 1;i<=M;i++) time2[i] = Integer.parseInt(st.nextToken());
			
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int i = 1;i<=K;i++) arrives[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(arrives);
			
			while(finished<K) {
				simulation();
			}
			
			for (int i = 1;i<=K;i++) {
				if (usedA[i] && usedB[i]) answer+=i;
			}
			
			answers[test] = answer == 0 ? -1 : answer;
			
		}
		
		for (int i = 1;i<=TestCnt;i++) System.out.println("#"+i+" "+answers[i]);
		
	}
	
	static void simulation() {
		repairing();
		fromWaitingToRepair();
		reception();
		fromWaitingToReception();
		approaching();
	}
	
	static void repairing() {
		for (int i = 1;i<=M;i++) {
			if (changu2[i].num!=0) {
				changu2[i].time = changu2[i].time-1;
				if (changu2[i].time==0) {
					if (i==B) usedB[changu2[i].num] = true;
					changu2[i] = new Guest(0,0);
					finished++;
				}
			}
		}
	}
	
	static void fromWaitingToRepair() {
		for (int i = 1;i<=M;i++) {
			if (changu2[i].num==0 && !waitQue2.isEmpty()) {
				int guestNum = waitQue2.peek();
				waitQue2.poll();
				changu2[i] = new Guest(guestNum,time2[i]);
			}
		}
	}
	
	static void reception() {
		for (int i = 1;i<=N;i++) {
			if (changu1[i].num!=0) {
				changu1[i].time = changu1[i].time-1;
				if (changu1[i].time==0) {
					if (i==A) usedA[changu1[i].num] = true;
					boolean find = false;
					for (int j = 1;j<=M;j++) {
						if (changu2[j].num==0) {
							changu2[j] = new Guest(changu1[i].num,time2[j]);
							find = true;
							break;
						}
					}
					if (!find) {
						waitQue2.add(changu1[i].num);
					}
					changu1[i] = new Guest(0,0);
				}
			}
		}
	}
	
	static void fromWaitingToReception() {
		for (int i = 1;i<=N;i++) {
			if (changu1[i].num==0 && !waitQue1.isEmpty()) {
				int guestNum = waitQue1.peek();
				waitQue1.poll();
				changu1[i] = new Guest(guestNum,time1[i]);
			}
		}
	}
	
	static void approaching() {
		for (int i = 1;i<=K;i++) {
			
			if (arrives[i]>0) arrives[i]--;
			
			else if (arrives[i]==0 && !checked[i]) {
				
				
				boolean find = false;
				for (int j = 1; j <= N; j++) {
					if (changu1[j].num == 0) {
						changu1[j] = new Guest(i, time1[j]);
						find = true;
						break;
					}
				}
				if (!find) {
					waitQue1.add(i);
				}
				checked[i] = true;
			}
		}
	}
    
}
