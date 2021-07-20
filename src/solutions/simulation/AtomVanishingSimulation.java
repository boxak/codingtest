package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AtomVanishingSimulation {

  static int N;
  static final int ZERO_X = 1005;
  static final int ZERO_Y = 1005;
  static final int[] dx = {0,0,-1,1};
  static final int[] dy = {-1,1,0,0};
  static Queue<Atom> atoms = new LinkedList<>();
  static int[][] map = new int[2*ZERO_Y][2*ZERO_X];
  static int answer;

  static class Atom {
    int x;
    int y;
    int dir;
    int energy;

    Atom(int x,int y,int dir,int energy) {
      this.x = x;
      this.y = y;
      this.dir = dir;
      this.energy = energy;
    }

    void go() {
      x = x + dx[dir];
      y = y + dy[dir];
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TestCnt = Integer.parseInt(br.readLine());

    for (int test = 1;test<=TestCnt;test++) {

      for (int i = 0;i<2*ZERO_Y;i++) {
        for (int j = 0;j<2*ZERO_X;j++) {
          map[i][j] = 0;
        }
      }

      atoms.clear();

      N = Integer.parseInt(br.readLine());
      answer = 0;

      for (int i = 0;i<N;i++) {
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str," ");

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        int energy = Integer.parseInt(st.nextToken());

        atoms.add(new Atom(ZERO_X+x,ZERO_Y+y,dir,energy));
        map[ZERO_Y+y][ZERO_X+x]++;
      }

      while(!atoms.isEmpty()) {
        simulation();
      }

      System.out.println("#"+test+" "+answer);
    }

  }

  static void simulation() {
    //원자들 이동
    //여기서 경계까지 가있는 원자들은 소멸시킴.
    int queSize = atoms.size();
    for (int i = 0;i<queSize;i++) {
      Atom atom = atoms.peek();
      atoms.poll();
      int x = atom.x;
      int y = atom.y;
      int dir = atom.dir;
      int energy = atom.energy;

      atom.go();
      int nx = atom.x;
      int ny = atom.y;
      map[y][x]--;
      if (isInside(y,x)) {
        map[ny][nx]++;
        atoms.add(atom);
      }
    }

    queSize = atoms.size();
    ArrayList<Atom> tempList = new ArrayList<>();

    for (int i = 0;i<queSize;i++) {
      Atom atom = atoms.peek();
      int x = atom.x;
      int y = atom.y;
      int energy = atom.energy;
      atoms.poll();

      if (map[y][x]>1) {
        answer+=energy;

      } else {
        atoms.add(atom);
      }
    }

  }

  static boolean isInside(int y,int x) {
    return (0<=y && y<2*ZERO_Y && 0<=x && x<2*ZERO_X);
  }
}
