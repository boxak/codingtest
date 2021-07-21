package solutions.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class TreasureBoxPassword {

  static int N,K;
  static String inputString;
  static ArrayList<String> strList;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\enliple\\Downloads\\sample_input (6).txt")));
    int TestCnt = Integer.parseInt(br.readLine());
    for (int test = 1;test<=TestCnt;test++) {
      String str = br.readLine();
      StringTokenizer st = new StringTokenizer(str," ");
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      strList = new ArrayList<>();

      inputString = br.readLine();

      for (int rot = 0;rot<N/4;rot++) {
        rotate();
      }

      int answer = getNumber();

      System.out.println("#"+test+" "+answer);
    }
  }

  static void rotate() {
    inputString = inputString.charAt(inputString.length()-1) + inputString.substring(0,inputString.length()-1);

    String subStr1 = inputString.substring(0,N/4);
    String subStr2 = inputString.substring(N/4,N/2);
    String subStr3 = inputString.substring(N/2,(3*N)/4);
    String subStr4 = inputString.substring((3*N)/4,N);

    String[] tempArr = new String[]{subStr1,subStr2,subStr3,subStr4};

    for (String subStrs : tempArr) {
      if (!strList.contains(subStrs)) {
        strList.add(subStrs);
      }
    }

  }

  static int getNumber() {
    ArrayList<Integer> list = new ArrayList<>();

    for (String subStrs : strList) {
      list.add(translateNum(subStrs));
    }

    Collections.sort(list, Comparator.reverseOrder());

    return list.get(K-1);
  }

  static int translateNum(String str) {
    int sum = 0;
    String tempStr = "";
    StringBuilder sb = new StringBuilder();
    for (int i = str.length()-1;i>=0;i--) {
      sb.append(str.charAt(i));
    }

    tempStr = sb.toString();

    for (int i = 0;i<tempStr.length();i++) {
      char c = tempStr.charAt(i);
      int a = 0;

      if (c>='0' && c<='9') a = c-'0';
      else if (c>='A' && c<='F') a = (c - 'A') + 10;

      sum+=a*((int)Math.pow(16,i));
    }
    return sum;
  }
}
