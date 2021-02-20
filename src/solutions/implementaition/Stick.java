package solutions.implementaition;

import java.util.Scanner;

public class Stick {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        int X = scan.nextInt();

        String binary = Integer.toBinaryString(X);

        int cntOne = 0;

        for (int i = 0;i<binary.length();i++) {
            if (binary.charAt(i)=='1') cntOne++;
        }

        System.out.println(cntOne);

    }
}
