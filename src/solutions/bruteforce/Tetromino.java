package solutions.bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tetromino {
	static int N,M;
	static int[][] map;
	
	static ArrayList<ArrayList<Pair>> tetrominos;
	
	static class Pair {
		int r,c;
		Pair(int r,int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		tetrominos = new ArrayList<>();
		
		for (int i = 0;i<N;i++) {
			str = br.readLine();
			st = new StringTokenizer(str," ");
			for (int j = 0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		setTetrominos();
		
		int answer = Integer.MIN_VALUE;
		
		for (ArrayList<Pair> list : tetrominos) {
			for (int i = 0;i<N;i++) {
				for (int j = 0;j<M;j++) {
					int sum = 0;
					int r = i;
					int c = j;
					boolean flag = true;
					for (Pair pair : list) {
						int nr = r + pair.r;
						int nc = c + pair.c;
						if (nr<0 || nr>=N || nc<0 || nc>=M) {
							flag = false;
							break;
						}
						sum+=map[nr][nc];
					}
					if (flag) {
						if (answer<sum) answer = sum;
					}
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	static void setTetrominos() {
		//일자형
		ArrayList<Pair> list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(1,0));
		list.add(new Pair(2,0));
		list.add(new Pair(3,0));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(0,1));
		list.add(new Pair(0,2));
		list.add(new Pair(0,3));
		tetrominos.add(list);
		
		//2x2형
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(0,1));
		list.add(new Pair(1,0));
		list.add(new Pair(1,1));
		tetrominos.add(list);
		
		//L자형
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(1,0));
		list.add(new Pair(2,0));
		list.add(new Pair(2,1));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(0,1));
		list.add(new Pair(0,2));
		list.add(new Pair(-1,2));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(0,1));
		list.add(new Pair(1,1));
		list.add(new Pair(2,1));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(1,0));
		list.add(new Pair(0,1));
		list.add(new Pair(0,2));
		tetrominos.add(list);
		
		//L자형 대칭
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(1,0));
		list.add(new Pair(2,0));
		list.add(new Pair(2,-1));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(0,1));
		list.add(new Pair(0,2));
		list.add(new Pair(1,2));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(0,1));
		list.add(new Pair(1,0));
		list.add(new Pair(2,0));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(1,0));
		list.add(new Pair(1,1));
		list.add(new Pair(1,2));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(1,0));
		list.add(new Pair(1,1));
		list.add(new Pair(2,1));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(1,0));
		list.add(new Pair(1,-1));
		list.add(new Pair(0,1));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(1,0));
		list.add(new Pair(1,-1));
		list.add(new Pair(2,-1));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(0,1));
		list.add(new Pair(1,1));
		list.add(new Pair(1,2));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(1,0));
		list.add(new Pair(2,0));
		list.add(new Pair(1,1));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(0,1));
		list.add(new Pair(0,2));
		list.add(new Pair(-1,1));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(0,1));
		list.add(new Pair(1,1));
		list.add(new Pair(-1,1));
		tetrominos.add(list);
		
		list = new ArrayList<>();
		list.add(new Pair(0,0));
		list.add(new Pair(0,1));
		list.add(new Pair(0,2));
		list.add(new Pair(1,1));
		tetrominos.add(list);
	}
}
