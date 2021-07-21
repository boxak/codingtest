package solutions.implementaition;

public class RepeatBinaryTransform {
  public int[] solution(String s) {
    int[] answer = {};
    answer = new int[2];
    StringBuilder sb = new StringBuilder(s);

    while(!"1".equals(sb.toString())) {
      String str = sb.toString();
      int len1 = str.length();
      str = str.replaceAll("0","");
      int len2 = str.length();
      int removeCnt = len1 - len2;
      answer[1]+=removeCnt;
      answer[0]++;
      String str2 = Integer.toBinaryString(len2);
      sb = new StringBuilder(str2);
    }

    return answer;
  }
}
