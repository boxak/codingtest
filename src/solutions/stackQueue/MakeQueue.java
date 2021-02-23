package solutions.stackQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MakeQueue {

  static class MyQueue {

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

    public int pop() {
      int num;
      if (rear > head) {
        num = list.get(head);
        head++;
      } else {
        num = -1;
      }
      return num;
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
      } else return -1;
    }

    public int back() {
      if (rear > head) {
        return list.get(rear - 1);
      } else return -1;
    }
  }

  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    ArrayList<Integer> answers = new ArrayList<>();
    MyQueue myQueue = new MyQueue();

    for (int i = 0;i<N;i++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str, " ");
      String command = st.nextToken();
      if ("push".equals(command)) {
        int x = Integer.parseInt(st.nextToken());
        myQueue.push(x);
      } else if ("pop".equals(command)) {
        answers.add(myQueue.pop());
      } else if ("size".equals(command)) {
        answers.add(myQueue.size());
      } else if ("empty".equals(command)) {
        answers.add(myQueue.empty());
      } else if ("front".equals(command)) {
        answers.add(myQueue.front());
      } else if ("back".equals(command)) {
        answers.add(myQueue.back());
      }
    }

    for (int num : answers) {
      System.out.println(num);
    }

  }

}
