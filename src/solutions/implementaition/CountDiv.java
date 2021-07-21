package solutions.implementaition;

public class CountDiv {
 public static int solution(int A, int B, int K) {
   if (A == B) {
     if (A%K == 0) return 1;
     else return 0;
   } else {
     int a = A/K;
     int b = B/K;
     if (A%K==0) return b - a +1;
     else return b - a;
   }
 }

 public static void main(String args[]) {
   int n = solution(10, 20, 5);
   System.out.println(n);
 }
}
