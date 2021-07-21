package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CarCenter {

    static int N,M,K,A,B;
    static int[] receiptTimes;
    static int[] repairTimes;
    static int[] timesToAlive;
    static Pair[] receipt;
    static Pair[] repair;
    static int cntFinish;
    static Queue<Pair> guestQue;
    static Queue<Integer> waitQueA;
    static Queue<Integer> waitQueB;
    static boolean[] hasUsed_A;
    static boolean[] hasUsed_B;
    static int time;

    static class Pair {
        int guestNum;
        int time;
        Pair(int x,int y) {
            guestNum = x;
            time = y;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TestCase = Integer.parseInt(br.readLine());

        for (int test = 1;test<=TestCase;test++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            time = 0;

            str = br.readLine();
            st = new StringTokenizer(str," ");

            receiptTimes = new int[N+1];
            repairTimes = new int[M+1];
            timesToAlive = new int[K+1];
            receipt = new Pair[N+1];
            repair = new Pair[M+1];
            hasUsed_A = new boolean[K+1];
            hasUsed_B = new boolean[K+1];
            guestQue = new LinkedList<>();
            waitQueA = new LinkedList<>();
            waitQueB = new LinkedList<>();

            cntFinish = 0;

            for (int i = 1;i<=N;i++) {
                receipt[i] = new Pair(0,0);
            }

            for (int i = 1;i<=M;i++) {
                repair[i] = new Pair(0,0);
            }

            for (int i = 1;i<=N;i++) {
                receiptTimes[i] = Integer.parseInt(st.nextToken());
            }

            str = br.readLine();
            st = new StringTokenizer(str," ");

            for (int i = 1;i<=M;i++) {
                repairTimes[i] = Integer.parseInt(st.nextToken());
            }

            str = br.readLine();
            st = new StringTokenizer(str," ");

            for (int i = 1;i<=K;i++) {
                timesToAlive[i] = Integer.parseInt(st.nextToken());
            }

            dataInsertIntoQue();
            while(cntFinish<K) {
                simulation();
                time++;
            }

            int answer = 0;

            for (int i = 1;i<=K;i++) {
                if (hasUsed_A[i] && hasUsed_B[i]) answer+=i;
            }

            System.out.println("#"+test+" "+(answer == 0 ? -1 : answer));

        }

    }
    // 접수 창구 대기 고객이 여러명이면 고객번호 낮은 순서대로 즉, 먼저 차량 정비소에 도착한 대로
    // 정비 창구에서는 이용했던 접수 창구번호가 작은 고객 우선
    // 빈 창구가 여러 곳이면 번호가 작은 곳으로 간다.
    static void simulation() {
        //정비 창구 처리 중인 고객 처리
        doRepair();
        //정비 창구가 비었으면 정비 창구 대기큐에 있는 고객 번호를 정비 창구로 넣는다
        enterRepair();
        //접수 창구 처리 중인 고객 처리, 접수가 끝나고 정비 창구가 비었으면 바로 넣는다.
        doReception();
        //접수 창구가 비었으면 접수 창구 대기큐에 있는 고객 번호를 접수 창구로 넣는다.
        enterReception();
        //도착한 고객들 처리, 접수 창구가 비었으면 바로 넣는다. 없으면 대기큐에 넣음.
        comeToCarCenter();
    }

    static void dataInsertIntoQue() {
        Arrays.sort(timesToAlive);
        for (int i = 1;i<=K;i++) {
            guestQue.add(new Pair(i,timesToAlive[i]));
        }
    }

    static void doRepair() {
        for (int i = 1;i<=M;i++) {
            if (repair[i].guestNum==0) continue;
            int guestNum = repair[i].guestNum;
            int time = repair[i].time;
            time--;
            if (time == 0) {
                repair[i] = new Pair(0,0);
                cntFinish++;
            } else {
                repair[i] = new Pair(guestNum,time);
            }
        }
    }

    static void enterRepair() {
        for (int i = 1;i<=M;i++) {
            if (waitQueB.isEmpty()) break;
            if (repair[i].guestNum==0) {
                int guestNum = waitQueB.peek();
                int time = repairTimes[i];
                waitQueB.poll();

                repair[i] = new Pair(guestNum,time);
                if (i==B) hasUsed_B[guestNum] = true;
            }
        }
    }

    static void doReception() {
        for (int i = 1;i<=N;i++) {
            if (receipt[i].guestNum==0) continue;
            int guestNum = receipt[i].guestNum;
            int time = receipt[i].time;
            time--;
            if (time == 0) {
                receipt[i] = new Pair(0,0);
                boolean isEmptyRepair = false;
                for (int j = 1;j<=M;j++) {
                    if (repair[j].guestNum == 0) {
                        repair[j] = new Pair(guestNum,repairTimes[j]);
                        isEmptyRepair = true;
                        if (j==B) hasUsed_B[guestNum] = true;
                        break;
                    }
                }
                if (!isEmptyRepair) {
                    waitQueB.add(guestNum);
                }
            } else {
                receipt[i] = new Pair(guestNum,time);
            }
        }
    }

    static void enterReception() {
        for (int i = 1;i<=N;i++) {
            if (waitQueA.isEmpty()) break;
            if (receipt[i].guestNum==0) {
                int guestNum = waitQueA.peek();
                int time = receiptTimes[i];
                waitQueA.poll();

                receipt[i] = new Pair(guestNum,time);
                if (i==A) hasUsed_A[guestNum] = true;
            }
        }
    }

    static void comeToCarCenter() {
        int queSize = guestQue.size();
        for (int ret = 0;ret<queSize;ret++) {
            int guestNum = guestQue.peek().guestNum;
            int time = guestQue.peek().time;
            guestQue.poll();
            if (time == 0) {
                boolean isEmptyReception = false;
                for (int i = 1;i<=N;i++) {
                    if (receipt[i].guestNum == 0) {
                        isEmptyReception = true;
                        receipt[i] = new Pair(guestNum,receiptTimes[i]);
                        if (i==A) hasUsed_A[guestNum] = true;
                        break;
                    }
                }
                if (!isEmptyReception) {
                    waitQueA.add(guestNum);
                }
            } else {
                time--;
                guestQue.add(new Pair(guestNum,time));
            }
        }
    }
}
