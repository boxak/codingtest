package solutions.stackQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyQueue {
  private int head;
  private int rear;
  private ArrayList<Integer> list;

  MyQueue() {
    list = new ArrayList<>();
    head = 0;
    rear = 0;
  }

  public void push(int x) {
    list.add(x);
    rear++;
  }

  public void pop() {
    head++;
  }

  public int size() {
    return rear - head;
  }

  public int empty() {
    return rear == head ? 1 : 0;
  }

  public int front() {
    if (rear > head) {
      return list.get(head);
    }
    else return -1;
  }

  public int back() {
    if (rear > head) {
      return list.get(rear-1);
    }
    else return -1;
  }

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] answers = new int[N];

    for (int i = 0;i<N;i++) {

    }

  }

}
