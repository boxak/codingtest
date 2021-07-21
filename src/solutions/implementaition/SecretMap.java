package solutions.implementaition;

public class SecretMap {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        answer = new String[n];

        for (int i = 0; i < n; i++) {
            int num1 = arr1[i];
            int num2 = arr2[i];
            String str1 = Integer.toBinaryString(num1);
            String str2 = Integer.toBinaryString(num2);
            String tmp1 = "";
            String tmp2 = "";
            int cnt1 = n - str1.length();
            int cnt2 = n - str2.length();

            for (int j = 0;j<cnt1;j++) {
                tmp1+="0";
            }
            for (int j = 0 ;j<cnt2;j++) {
                tmp2+="0";
            }

            String result1 = tmp1+str1;
            String result2 = tmp2+str2;

            String finalStr = "";

            for (int j = 0;j<n;j++) {
                String block = " ";
                if (result1.charAt(j)=='1' || result2.charAt(j)=='1') {
                    block = "#";
                }
                finalStr+=block;
            }
            answer[i] = finalStr;
        }

        return answer;
    }
}
