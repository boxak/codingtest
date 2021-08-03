package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RobotOnConveyorBelt {
	static int N,K;
	static int[] duration1;
	static int[] duration2;
	static boolean[] belt1;
	static boolean[] belt2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		duration1 = new int[N];
		duration2 = new int[N];
		belt1 = new boolean[N];
		belt2 = new boolean[N];
		
		int answer = 1;
		
		str = br.readLine();
		st = new StringTokenizer(str," ");
		
		for (int i = 0;i<N;i++) {
			duration1[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0;i<N;i++) {
			duration2[i] = Integer.parseInt(st.nextToken());
		}
		
		while(true) {
			simulation();
			if (!finishedCheck()) {
				answer++;
			} else break;
		}
		
		System.out.println(answer);
		
	}	
	
	static void simulation() {
		rotation();
		moveRobot();
		insertRobot();
	}
	
	static void rotation() {
		rotation1();
		rotation2();
	}
	
	static void rotation1() {
		int[] arr1 = new int[N];
		int[] arr2 = new int[N];
		
		for (int i = 0;i<N-1;i++) {
			arr1[i+1] = duration1[i];
		}
		
		arr1[0] = duration2[N-1];
		
		for (int i = 0;i<N-1;i++) {
			arr2[i+1] = duration2[i];
		}
		
		arr2[0] = duration1[N-1];
		
		for (int i = 0;i<N;i++) {
			duration1[i] = arr1[i];
		}
		
		for (int i = 0;i<N;i++) {
			duration2[i] = arr2[i];
		}
		
	}
	
	static void rotation2() {
		boolean[] arr1 = new boolean[N];
		boolean[] arr2 = new boolean[N];
		
		for (int i = 0;i<N-1;i++) {
			arr1[i+1] = belt1[i];
		}
		
		arr1[0] = belt2[N-1];
		
		for (int i = 0;i<N-1;i++) {
			arr2[i+1] = belt2[i];
		}
		
		arr2[0] = belt1[N-1];
		
		for (int i = 0;i<N;i++) {
			belt1[i] = arr1[i];
		}
		
		for (int i = 0;i<N;i++) {
			belt2[i] = arr2[i];
		}
		
		if(belt1[N-1]) belt1[N-1] = false;
	}
	
	static void moveRobot() {
		for (int i = N-2;i>=0;i--) {
			if (belt1[i]) {
				if (!belt1[i+1] && duration1[i+1]>=1) {
					belt1[i+1] = true;
					belt1[i] = false;
					duration1[i+1]--;
				}
			}
		}
		
		if (belt1[N-1]) belt1[N-1] = false;
		
	}
	
	static void insertRobot() {
		if (duration1[0]>0) {
			belt1[0] = true;
			duration1[0]--;
		}
	}
	
	static boolean finishedCheck() {
		int cnt = 0;
		for (int i = 0;i<N;i++) {
			if (duration1[i]==0) cnt++;
			if (duration2[i]==0) cnt++;
		}
		
		return cnt>=K;
	}
	
	
}
