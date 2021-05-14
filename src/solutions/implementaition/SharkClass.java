package solutions.implementaition;

import java.util.*;
import java.io.*;

public class SharkClass {

    static class Pair {
        int student;
        ArrayList<Integer> friends;
        Pair(int a,int b,int c,int d,int e) {
            student = a;
            friends = new ArrayList<>();
            friends.add(b);
            friends.add(c);
            friends.add(d);
            friends.add(e);
        }
    }

    static ArrayList<Pair> list;
    static int N;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int answer;
    static int[][] map1;
    static int[][] map2;
    static int[][] map3;
    static int maxFriends;
    static int maxEmpty;
    static int[] scores = {0,1,10,100,1000};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        answer = 0;
        map1 = new int[N][N];
        map2 = new int[N][N];
        map3 = new int[N][N];

        for (int i = 0;i<N*N;i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");
            int student = Integer.parseInt(st.nextToken());
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            int f3 = Integer.parseInt(st.nextToken());
            int f4 = Integer.parseInt(st.nextToken());
            list.add(new Pair(student,f1,f2,f3,f4));
        }

        for (int i = 0;i<N*N;i++) {
            int student = list.get(i).student;
            ArrayList<Integer> friends = list.get(i).friends;
            maxFriends = 0;
            maxEmpty = 0;
            initMap();
            findFriends(friends);
            findEmpty();
            sit(student);
        }

        answer = sumOfHappy();
        System.out.println(answer);
    }

    static void initMap() {
        for (int i = 0;i<N;i++) {
            for (int j = 0;j<N;j++) {
                map2[i][j] = 0;
            }
        }
        for (int i = 0;i<N;i++) {
            for (int j = 0;j<N;j++) {
                map3[i][j] = 0;
            }
        }
    }

    static void findFriends(ArrayList<Integer> friends) {
        for (int i = 0;i<N;i++) {
            for (int j = 0;j<N;j++) {
                if (map1[i][j]!=0) continue;
                int cnt = 0;
                for (int d = 0;d<4;d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (!checkOut(nr,nc)) continue;
                    if (friends.contains(map1[nr][nc])) {
                        cnt++;
                    }
                }
                if (cnt>maxFriends) maxFriends = cnt;
                map2[i][j] = cnt;
            }
        }
    }

    static void findEmpty() {
        for (int i = 0;i<N;i++) {
            for (int j = 0;j<N;j++) {
                if (map1[i][j]!=0) continue;
                if (map2[i][j]!=maxFriends) continue;
                int cnt = 0;
                for (int d = 0;d<4;d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (!checkOut(nr,nc)) continue;
                    if (map1[nr][nc]==0) {
                        cnt++;
                    }
                }
                if (cnt > maxEmpty) maxEmpty = cnt;
                map3[i][j] = cnt;
            }
        }
    }

    static void sit(int student) {
        for (int i = 0;i<N;i++) {
            boolean flag = false;
            for (int j = 0;j<N;j++) {
                if (map1[i][j]==0 && map2[i][j]==maxFriends && map3[i][j]==maxEmpty) {
                    map1[i][j] = student;
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }
    }

    static boolean checkOut(int r,int c) {
        return !(r<0 || r>=N || c<0 || c>=N);
    }

    static int sumOfHappy() {
        int sum = 0;
        for (int i = 0;i<N;i++) {
            for (int j = 0;j<N;j++) {
                int cnt = 0;
                int student = map1[i][j];
                ArrayList<Integer> friends = new ArrayList<>();
                for (Pair pair : list) {
                    if (pair.student == student) {
                        friends = pair.friends;
                        break;
                    }
                }

                for (int d = 0;d<4;d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (!checkOut(nr,nc)) continue;
                    if (friends.contains(map1[nr][nc])) {
                        cnt++;
                    }
                }
                sum+=scores[cnt];
            }
        }
        return sum;
    }
}
