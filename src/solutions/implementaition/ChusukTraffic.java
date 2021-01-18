package solutions.implementaition;

public class ChusukTraffic {
  public static int solution(String[] lines) {
    int answer = 0;
    int arr[] = new int[86400000];

    for (int i = 0;i< lines.length;i++) {
      String line = lines[i];
      String[] strArr = line.split(" ");
      String time = strArr[1];
      String elapse = strArr[2];

      String[] timeArr = time.split(":|\\.");
      int milSecond = Integer.parseInt(timeArr[2]+timeArr[3]);
      int minute = 60000 * Integer.parseInt(timeArr[1]);
      int hour = 3600000 * Integer.parseInt(timeArr[0]);
      int finishTime = hour + minute + milSecond;

      double d = Double.parseDouble(elapse.replace("s","")) * 1000;
      int cnt = (int)d;
      int startTime = Math.max(finishTime - cnt + 1, 0);

      for (int j = finishTime;j>=startTime;j--) {
        arr[j]++;
      }
    }

    return answer;
  }

  public static void main(String args[]) {
    String s = "0.351";
    double a = Double.parseDouble(s);
    System.out.println(a*1000);

    String date = "20:59:57.421";
    String[] arr = date.split(":|\\.");
    System.out.println(arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]);
    System.out.println(solution(new String[]{
        "2016-09-15 01:00:04.002 2.0","2016-09-15 01:00:07.000 2s"
    }));
  }
}
