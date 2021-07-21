package solutions.implementaition;

import java.util.HashMap;

public class TouchKeypad {

    static class Pair {
        int r;
        int c;
        Pair(int r,int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static String solution(int[] numbers, String hand) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        HashMap<String,Pair> map = new HashMap<>();
        map.put("1",new Pair(0,0));
        map.put("2",new Pair(0,1));
        map.put("3",new Pair(0,2));
        map.put("4",new Pair(1,0));
        map.put("5",new Pair(1,1));
        map.put("6",new Pair(1,2));
        map.put("7",new Pair(2,0));
        map.put("8",new Pair(2,1));
        map.put("9",new Pair(2,2));
        map.put("*",new Pair(3,0));
        map.put("0",new Pair(3,1));
        map.put("#",new Pair(3,2));

        int leftR = map.get("*").r;
        int leftC = map.get("*").c;
        int rightR = map.get("#").r;
        int rightC = map.get("#").c;

        for (int num : numbers) {
            int keyR = map.get(Integer.toString(num)).r;
            int keyC = map.get(Integer.toString(num)).c;
            if (num==1 || num==4 || num==7) {
                sb.append("L");
                leftR = keyR;
                leftC = keyC;
            } else if(num==3 || num==6 || num==9) {
                sb.append("R");
                rightR = keyR;
                rightC = keyC;
            } else {
                int distR = Math.abs(keyR-rightR)+Math.abs(keyC-rightC);
                int distL = Math.abs(keyR-leftR)+Math.abs(keyC-leftC);
                if (distR>distL) {
                    sb.append("L");
                    leftR = keyR;
                    leftC = keyC;
                } else if(distR<distL) {
                    sb.append("R");
                    rightR = keyR;
                    rightC = keyC;
                } else {
                    if ("left".equals(hand)) {
                        sb.append("L");
                        leftR = keyR;
                        leftC = keyC;
                    } else {
                        sb.append("R");
                        rightR = keyR;
                        rightC = keyC;
                    }
                }
            }
        }
        answer = sb.toString();
        return answer;
    }

    public static void main(String args[]) {
        System.out.println(solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0},"right"));
    }
}
