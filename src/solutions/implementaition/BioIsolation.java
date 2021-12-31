package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BioIsolation {

	static int N, M, K;
	static int[][] map;
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };
	static ArrayList<Pair> list;
	static ArrayList<Pair> tempList;

	static class Pair implements Comparable<Pair> {
		int r, c, num, dir;

		Pair(int r, int c, int num, int dir) {
			this.r = r;
			this.c = c;
			this.num = num;
			this.dir = dir;
		}

		@Override
		public int compareTo(Pair pair) {
			return pair.num - this.num;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TestCnt = Integer.parseInt(br.readLine());
		int[] answers = new int[TestCnt + 1];

		for (int test = 1; test <= TestCnt; test++) {

			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			list = new ArrayList<>();
			tempList = new ArrayList<>();

			for (int i = 0; i < K; i++) {
				str = br.readLine();
				st = new StringTokenizer(str, " ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				if (dir == 1)
					dir = 0;
				else if (dir == 2)
					dir = 2;
				else if (dir == 3)
					dir = 3;
				else if (dir == 4)
					dir = 1;

				list.add(new Pair(r, c, num, dir));
			}

			int answer = 0;

			for (int time = 0; time < M; time++) {
				move();
				arrange();
			}

			for (Pair pair : list) {
				answer += pair.num;
			}

			answers[test] = answer;
		}

		for (int i = 1; i <= TestCnt; i++)
			System.out.println("#" + i + " " + answers[i]);
	}

	static void move() {
		int size = list.size();
		for (int i = 0; i < size; i++) {
			int r = list.get(i).r;
			int c = list.get(i).c;
			int num = list.get(i).num;
			int dir = list.get(i).dir;

			int nr = r + dr[dir];
			int nc = c + dc[dir];

			map[nr][nc] += num;
			list.set(i, new Pair(nr, nc, num, dir));
		}
	}

	static void arrange() {
		Collections.sort(list);
		tempList.clear();
		int size = list.size();

		for (int i = 0; i < size; i++) {
			int r = list.get(i).r;
			int c = list.get(i).c;
			int num = list.get(i).num;
			int dir = list.get(i).dir;

			if (r == 0 || r == N - 1 || c == 0 || c == N - 1) {
				tempList.add(new Pair(r, c, num / 2, (dir + 2) % 4));
				map[r][c] = 0;
			} else {
				if (map[r][c] > 0) {
					tempList.add(new Pair(r, c, map[r][c], dir));
					map[r][c] = 0;
				}
			}
		}

		list.clear();

		for (Pair pair : tempList)
			list.add(new Pair(pair.r, pair.c, pair.num, pair.dir));

	}

}
