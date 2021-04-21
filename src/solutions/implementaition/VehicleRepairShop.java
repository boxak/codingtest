package solutions.implementaition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class VehicleRepairShop {

  static int N,M,K,A,B;
  static int[] aArr,bArr;
  static int[] tArr;
  static int answer;
  static Queue<Customer> aque,bque;
  static Customer[] reception,repair;
  static boolean[] check1,check2;
  static boolean[] checkCus;
  static boolean[] possible1,possible2;
  static int[] dist;
  static int cntFinish;
  static int timeCnt;

  static class Customer implements Comparable<Customer>{
    int inx;
    int time;
    int num;
    Customer(int x,int y, int z) {
      inx = x;
      time = y;
      num = z;
    }

    public int compareTo(Customer customer) {
      return this.num - customer.num;
    }
  }

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\enliple\\Downloads\\sample_input.txt"));
    int TestCnt = Integer.parseInt(br.readLine());

    for (int test = 1;test<=TestCnt;test++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      A = Integer.parseInt(st.nextToken());
      B = Integer.parseInt(st.nextToken());

      aArr = new int[N];
      bArr = new int[M];
      tArr = new int[K];
      reception = new Customer[N];
      repair = new Customer[M];

      for (int i = 0;i<N;i++) reception[i] = new Customer(-1,0,0);
      for (int i = 0;i<M;i++) repair[i] = new Customer(-1,0,0);

      dist = new int[K];
      check1 = new boolean[N];
      check2 = new boolean[M];
      checkCus = new boolean[K];
      possible1 = new boolean[K];
      possible2 = new boolean[K];
      answer = 0;
      cntFinish = 0;
      aque = new LinkedList<>();
      bque = new LinkedList<>();
      timeCnt = 0;

      str = br.readLine();
      st = new StringTokenizer(str," ");

      for (int i = 0;i<N;i++) {
        aArr[i] = Integer.parseInt(st.nextToken());
      }

      str = br.readLine();
      st = new StringTokenizer(str," ");

      for (int i = 0;i<M;i++) {
        bArr[i] = Integer.parseInt(st.nextToken());
      }

      str = br.readLine();
      st = new StringTokenizer(str," ");

      for (int i = 0;i<K;i++) {
        tArr[i] = Integer.parseInt(st.nextToken());
        dist[i] = tArr[i];
      }

      while(cntFinish < K) {

        repairService();
        receptionService();
        moveToShop();
//        System.out.printf("time : %d / ",timeCnt);
//        for (int i = 0;i<N;i++) System.out.printf("%d ",reception[i].inx+1);
//        for (int i = 0;i<M;i++) System.out.printf("%d ",repair[i].inx+1);
//        System.out.println();
        timeCnt++;
      }

      for (int i = 0;i<K;i++) {
        if (possible1[i] && possible2[i]) {
          answer = answer+i+1;
        }
      }
      if (answer==0) System.out.println(-1);
      else System.out.println("#"+test+" "+answer);
    }

  }

  static void repairService() {
    for (int i = 0;i<M;i++) {
      Customer customer = repair[i];
      if (check2[i]) {
        repair[i] = new Customer(customer.inx, customer.time-1,customer.num);
        if (customer.time-1==0) {
          check2[i] = false;
          repair[i] = new Customer(-1,0,0);
          cntFinish++;
        }
      }
    }
  }

  static void receptionService() {
    for (int i = 0;i<N;i++) {
      Customer customer = reception[i];
      if (check1[i]) {
        reception[i] = new Customer(customer.inx, customer.time-1, customer.num);
        if (customer.time-1==0) {
          check1[i] = false;
          reception[i] = new Customer(-1,0,0);
          bque.add(new Customer(customer.inx, 0,i));
        }
      }
    }

    ArrayList<Customer> tempList = new ArrayList<>();

    for (Customer customer : bque) tempList.add(new Customer(customer.inx, customer.time, customer.num));
    bque.clear();

    Collections.sort(tempList);

    for (Customer customer : tempList) bque.add(new Customer(customer.inx, customer.time, customer.num));

    for (int i = 0;i<M;i++) {
      if (!check2[i] && !bque.isEmpty()) {
        int num = bque.peek().inx;
        int num2 = bque.peek().num;
        bque.poll();

        repair[i] = new Customer(num,bArr[i],num2);
        check2[i] = true;
        if (i==B-1) {
          possible2[num] = true;
        }
      }
    }
  }

  static void moveToShop() {
    for (int i = 0;i<K;i++) {
      int d = dist[i];
      if (d == 0 && !checkCus[i]) {
        checkCus[i] = true;
        aque.add(new Customer(i,0,i));
      }
      if (d > 0) {
        d--;
        dist[i] = d;
        if (d == 0 && !checkCus[i]) {
          checkCus[i] = true;
          aque.add(new Customer(i,0,i));
        }
      }
    }

    ArrayList<Customer> tempList = new ArrayList<>();

    for (Customer customer : aque) tempList.add(new Customer(customer.inx, customer.time, customer.num));
    aque.clear();

    Collections.sort(tempList);

    for (Customer customer : tempList) aque.add(new Customer(customer.inx, customer.time, customer.num));

    for (int i = 0;i<N;i++) {
      if (!check1[i] && !aque.isEmpty()) {
        int num = aque.peek().inx;
        int num2 = aque.peek().num;
        aque.poll();
        reception[i] = new Customer(num,aArr[i],num2);
        check1[i] = true;
        if (i==A-1) {
          possible1[num] = true;
        }
      }
    }
  }
}
