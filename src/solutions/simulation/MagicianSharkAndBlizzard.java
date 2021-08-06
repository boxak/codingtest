package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MagicianSharkAndBlizzard {
	
	static class Pair {
		int r,c;
		Pair(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static class Group {
		int num;
		int cnt;
		Group(int x,int y) {
			num = x;
			cnt = y;
		}
	}
	
	static int N,M;
	static final int[] dr = {0,-1,1,0,0};
	static final int[] dc = {0,0,0,-1,1};
	static Pair[] arr;
	static int[][] numberMap;
	static int[][] map;
	static int answer;
	static int[] di,si;
	static ArrayList<Integer> marbleList;
	static ArrayList<Integer> marbleList2;
	static ArrayList<Group> groupList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		numberMap = new int[N+1][N+1];
		arr = new Pair[N*N+1];
		di = new int[M];
		si = new int[M];
		marbleList = new ArrayList<>();
		marbleList2 = new ArrayList<>();
		groupList = new ArrayList<>();
		
		for (int i = 1;i<=N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 1;j<=N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0;i<M;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			di[i] = Integer.parseInt(st.nextToken());
			si[i] = Integer.parseInt(st.nextToken());
		}
		
		init();
		
		simulation();
		
//		for (int i = 1;i<=N;i++) {
//			for (int j = 1;j<=N;j++) {
//				System.out.printf("%d ", numberMap[i][j]);
//			}
//			System.out.println();
//		}
//		
//		System.out.println();
//		
//		for (int i = 1;i<=N*N-1;i++) System.out.println(arr[i].r+" "+arr[i].c);
		
		
		System.out.println(answer);
		
	}
	// 1 : 위, 2: 아래, 3 : 왼쪽, 4 : 오른쪽
	static void init() {
		int r = N/2+1;
		int c = N/2+1;
		int d = 3;
		int changeDir = 0;
		int goCount = 0;
		int goCountLimit = 1;
		int inx = 1;
		
		for (int i = 0;i<=N*N;i++) arr[i] = new Pair(0,0);
		
		for (int i = 0;i<N*N-1;i++) {
			r = r + dr[d];
			c = c + dc[d];
			arr[inx] = new Pair(r,c);
			numberMap[r][c] = inx++;
			goCount++;
			if (goCount==goCountLimit) {
				goCount = 0;
				if (d == 1) d = 3;
				else if (d == 2) d = 4;
				else if (d == 3) d = 2;
				else if (d == 4) d = 1;
				if (changeDir==1) {
					changeDir = 0;
					goCountLimit++;
				} else {
					changeDir++;
				}
			}
		}
		
		for (int i = 0;i<N*N-1;i++) {
			int rr = arr[i].r;
			int cc = arr[i].c;
			if (map[rr][cc]!=0) {
				marbleList.add(map[rr][cc]);
			}
		}
		
	}
	
	static void simulation() {
		for (int time = 0;time<M;time++) {
			magic(time);
			move();
			while(explosion()) {
				move();
			}
			grouping();
		}
	}
	
	static void magic(int time) {
		int d = di[time];
		int s = si[time];
		int r = N/2+1;
		int c = N/2+1;
		for (int i = 1;i<=s;i++) {
			int nr = r+i*dr[d];
			int nc = c+i*dc[d];
			map[nr][nc] = 0;
		}
	}
	
	static void move() {
		marbleList.clear();
		for (int i = 0;i<N*N-1;i++) {
			int rr = arr[i].r;
			int cc = arr[i].c;
			if (map[rr][cc]!=0) {
				marbleList.add(map[rr][cc]);
			}
		}
		
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				map[i][j] = 0;
			}
		}
		
		for (int i = 0;i<marbleList.size();i++) {
			int inx = i+1;
			int r = arr[inx].r;
			int c = arr[inx].c;
			map[r][c] = marbleList.get(i);
		}
	}
	
	static boolean explosion() {
		boolean flag = false;
		
		if (marbleList.size()>1) {
			int seqNum = marbleList.get(0);
			int size = marbleList.size();
			ArrayList<Integer> inxList = new ArrayList<>();
			inxList.add(1);
			
			for (int i = 1;i<size;i++) {
				int num = marbleList.get(i);
				if (num==seqNum) {
					inxList.add(i+1);
				} else {
					if (inxList.size()>=4) {
						answer+=seqNum*inxList.size();
						flag = true;
						for (int inx : inxList) {
							int r = arr[inx].r;
							int c = arr[inx].c;
							map[r][c] = 0;
						}
					}
					inxList.add(i+1);
					seqNum = marbleList.get(i);
				}
			}
			
			if (inxList.size()>=4) {
				answer+=seqNum*inxList.size();
				flag = true;
				for (int inx : inxList) {
					int r = arr[inx].r;
					int c = arr[inx].c;
					map[r][c] = 0;
				}
			}
			
		}
		return flag;
	}
	
	static void grouping() {
		groupList.clear();
		int size = marbleList.size();
		if (size>0) {
			int seqNum = marbleList.get(0);
			int cnt = 1;
			
			for (int i = 1;i<size;i++) {
				int num = marbleList.get(i);
				if (num==seqNum) {
					cnt++;
				} else {
					groupList.add(new Group(seqNum,cnt));
					seqNum = num;
					cnt = 1;
				}
			}
			
			groupList.add(new Group(seqNum,cnt));
			
			marbleList.clear();
			
			for (int i = 1;i<=N;i++) {
				for (int j = 1;j<=N;j++) {
					map[i][j] = 0;
				}
			}
			
			int inx = 1;
			
			for (Group group : groupList) {
				int rr1 = arr[inx].r;
				int cc1 = arr[inx].c;
				map[rr1][cc1] = group.cnt;
				inx++;
				if (inx>=N*N) break;
				int rr2 = arr[inx].r;
				int cc2 = arr[inx].c;
				map[rr2][cc2] = group.num;
				inx++;
				if (inx>=N*N) break;
			}
			
		}
	}
	
	static void print(String str) {
		System.out.println(str);
		for (int i = 1;i<=N;i++) {
			for (int j = 1;j<=N;j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
		System.out.println();
	}
  
}
