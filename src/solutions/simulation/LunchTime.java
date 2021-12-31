package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class LunchTime {
	static int N;
	static int[][] map;
	static final int[] dr = {-1,0,1,0};
	static final int[] dc = {0,1,0,-1};
	static int[] arr;
	static Queue<Integer> waitQue1,waitQue2;
	static Queue<Integer> que1,que2;
	static Queue<Person> peopleQue;
	static ArrayList<Person> peopleList;
	static int r1,c1,r2,c2,t1,t2;
	static int answer,finished;
	
	static class Person {
		int r,c,num,dist;
		Person(int r,int c,int num,int dist) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TestCnt = Integer.parseInt(br.readLine());
		int[] answers = new int[TestCnt+1];
		
		for (int test = 1;test<=TestCnt;test++) {
			N = Integer.parseInt(br.readLine());
			r1 = -1;
			c1 = -1;
			r2 = -1;
			c2 = -1;
			t1 = -1;
			t2 = -1;
			
			map = new int[N+1][N+1];
			que1 = new LinkedList<>();
			que2 = new LinkedList<>();
			waitQue1 = new LinkedList<>();
			waitQue2 = new LinkedList<>();
			peopleList = new ArrayList<>();
			peopleQue = new LinkedList<>();
			answer = Integer.MAX_VALUE;
			
			for (int i = 1;i<=N;i++) {
				String str = br.readLine();
				StringTokenizer st = new StringTokenizer(str," ");
				for (int j = 1;j<=N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j]==1) {
						peopleList.add(new Person(i,j,0,0));
					} else if (map[i][j]>=2) {
						if (t1==-1) {
							r1 = i;
							c1 = j;
							t1 = map[i][j];
						} else {
							r2 = i;
							c2 = j;
							t2 = map[i][j];
						}
					}
				}
			}
			arr = new int[peopleList.size()];
			
			dfs(0);
			
			answers[test] = answer;
			
		}
		
		for (int i = 1;i<=TestCnt;i++) System.out.println("#"+i+" "+answers[i]);
		
	}
	
	static void dfs(int x) {
		if (x==peopleList.size()) {
			simulation();
			return;
		}
		
		arr[x] = 1;
		dfs(x+1);
		arr[x] = 2;
		dfs(x+1);
	}
	
	static void simulation() {
		int peopleCnt = peopleList.size();
		for (int i = 0;i<peopleCnt;i++) {
			Person person = peopleList.get(i);
			int r = person.r;
			int c = person.c;
			
			int num = arr[i];
			int dist = 0;
			
			if (num==1) {
				dist = Math.abs(r1-r) + Math.abs(c1-c);
			} else if (num==2) {
				dist = Math.abs(r2-r) + Math.abs(c2-c);
			}
			
			peopleQue.add(new Person(person.r,person.c,num,dist));
		}
		
		int cnt = 0;
		finished = peopleList.size();
		
		while(finished>0) {
			cnt++;
			goDownStair();
			insertStairFromWaiting();
			approachingToStair();
		}
		
		if (cnt<answer) answer = cnt+1;
	}
	
	static void goDownStair() {
		int queSize1 = que1.size();
		int queSize2 = que2.size();
		for (int i = 0;i<queSize1;i++) {
			int time = que1.peek();
			que1.poll();
			time--;
			if (time > 0) {
				que1.add(time);
			} else {
				finished--;
			}
		}
		
		for (int i = 0;i<queSize2;i++) {
			int time = que2.peek();
			que2.poll();
			time--;
			if (time > 0) {
				que2.add(time);
			} else {
				finished--;
			}
		}
	}
	
	static void insertStairFromWaiting() {
		while(que1.size()<3 && !waitQue1.isEmpty()) {
			waitQue1.poll();
			que1.add(t1);
		}
		while(que2.size()<3 && !waitQue2.isEmpty()) {
			waitQue2.poll();
			que2.add(t2);
		}
	}
	
	static void approachingToStair() {
		int queSize = peopleQue.size();
		
		for (int i = 0;i<queSize;i++) {
			int r = peopleQue.peek().r;
			int c = peopleQue.peek().c;
			int num = peopleQue.peek().num;
			int dist = peopleQue.peek().dist;
			peopleQue.poll();
			dist--;
			if (dist>0) {
				peopleQue.add(new Person(r,c,num,dist));
			} else if (dist==0) {
				if (que1.size()==3 && num==1) {
					waitQue1.add(0);
				}
				if (que2.size()==3 && num==2) {
					waitQue2.add(0);
				}
				if (que1.size()<3 && num==1) {
					que1.add(t1);
				}
				if (que2.size()<3 && num==2) {
					que2.add(t2);
				}
			}
		}
		
	}
	
}
