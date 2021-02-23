package solutions.stackQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MakeStack {
    static class MyStack {
        private int top;
        private ArrayList<Integer> list;

        MyStack() {
            top = 0;
            list = new ArrayList<>();
        }

        public void push(int x) {
            top++;
            list.add(x);
        }

        public int pop() {
            if (top == 0) {
                return -1;
            } else {
                int num = list.get(top-1);
                list.remove(top-1);
                top--;
                return num;
            }
        }

        public int size() {
            return top;
        }

        public int empty() {
            return top == 0 ? 1 : 0;
        }

        public int top() {
            if (top==0) {
                return -1;
            } else {
                return list.get(top-1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> answers = new ArrayList<>();
        MyStack myStack = new MyStack();

        for (int i = 0;i<N;i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str," ");
            String command = st.nextToken();
            if ("push".equals(command)) {
                int x = Integer.parseInt(st.nextToken());
                myStack.push(x);
            } else if ("pop".equals(command)) {
                answers.add(myStack.pop());
            } else if ("size".equals(command)) {
                answers.add(myStack.size());
            } else if ("empty".equals(command)) {
                answers.add(myStack.empty());
            } else if ("top".equals(command)) {
                answers.add(myStack.top());
            }
        }

        for (int num : answers) {
            System.out.println(num);
        }

    }

}
