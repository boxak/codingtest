package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Gear {

    static class Pair {
        int num;
        int dir;

        Pair(int x, int y) {
            num = x;
            dir = y;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        String second = br.readLine();
        String third = br.readLine();
        String fourth = br.readLine();
        int K = Integer.parseInt(br.readLine());
        String[] gears = {first, second, third, fourth};

        Pair[] rotations = new Pair[K];

        for (int i = 0; i < K; i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str, " ");
            int gearNumber = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());
            rotations[i] = new Pair(gearNumber, dir);
        }

        for (int i = 0; i < K; i++) {
            int rotationInfo[] = new int[4];
            int inx = rotations[i].num;
            rotationInfo[inx] = rotations[i].dir;
            for (int j = inx - 1; j >= 0; j--) {
                if (gears[j].charAt(2) != gears[j + 1].charAt(6) && rotationInfo[j + 1] != 0) {
                    rotationInfo[j] = -rotationInfo[j + 1];
                }
            }
            for (int j = inx + 1; j < 4; j++) {
                if (gears[j].charAt(6) != gears[j - 1].charAt(2) && rotationInfo[j - 1] != 0) {
                    rotationInfo[j] = -rotationInfo[j - 1];
                }
            }
            for (int j = 0; j < 4; j++) {
                if (rotationInfo[j]!=0) {
                    move(gears,j,rotationInfo[j]);
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < 4; i++) {
            answer += (gears[i].charAt(0) - '0') * ((int) Math.pow(2.0, i));
        }

        System.out.println(answer);
    }

    static void move(String[] gears,int gearNumber,int dir) {
        String str = gears[gearNumber];
        String temp = "";
        if (dir == 1) {
            temp+=str.charAt(7);
            for (int i =0;i<7;i++) {
                temp+=str.charAt(i);
            }
        } else {
            for (int i = 1;i<=7;i++) {
                temp+=str.charAt(i);
            }
            temp+=str.charAt(0);
        }
        gears[gearNumber] = temp;
    }
}
