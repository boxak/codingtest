package solutions.implementaition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PredictFootball {

    static int[] arr = new int[6];
    static double[] answers = new double[4];
    static String[] countries;
    static String[] predictions;
    static Map<String, Integer> countryMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        countries = str.split(" ");
        countryMap = new HashMap<>();
        for (int i = 0;i<4;i++) {
            countryMap.put(countries[i], i);
        }
        predictions = new String[6];

        for (int i = 0;i<6;i++) {
            predictions[i] = br.readLine();
        }

        dfs(0);

        for (int i = 0;i<4;i++) {
            System.out.println(String.format("%.10f",answers[i]));
        }
    }

    static void dfs(int x) {
        if (x==6) {
            calculate();
            return;
        }

        for (int i = 0;i<3;i++) {
            arr[x] = i;
            dfs(x+1);
        }
    }

    static void calculate() {
        double probability = 1;
        Map<String, Integer> pointMap = new HashMap<>();
        pointMap.put(countries[0], 0);
        pointMap.put(countries[1], 0);
        pointMap.put(countries[2], 0);
        pointMap.put(countries[3], 0);
        for (int i = 0;i<6;i++) {
            int idx = arr[i];
            String[] split = predictions[i].split(" ");

            int point1 = pointMap.get(split[0]);
            int point2 = pointMap.get(split[1]);
            probability*=Double.parseDouble(split[idx+2]);
            switch (idx) {
                case 0 :
                    pointMap.put(split[0], point1+3);
                    break;
                case 1 :
                    pointMap.put(split[0], point1+1);
                    pointMap.put(split[1], point2+1);
                    break;
                case 2 :
                    pointMap.put(split[1], point2+3);
                    break;
            }
        }

        int maxScore = Integer.MIN_VALUE;
        int secondScore = Integer.MIN_VALUE;

        for (String key : pointMap.keySet()) {
            int score = pointMap.get(key);
            if (score>maxScore) maxScore = score;
        }

        for (String key : pointMap.keySet()) {
            int score = pointMap.get(key);
            if (score==maxScore) continue;
            if (score>secondScore) secondScore = score;
        }

        if (secondScore==Integer.MIN_VALUE) {
            for (int i = 0;i<4;i++) {
                answers[i]+=probability*0.5;
            }
        } else {
            double[] tempArr = new double[4];
            int maxCnt = 0;
            int secondCnt = 0;

            for (String key : pointMap.keySet()) {
                int score = pointMap.get(key);
                if (score==maxScore) maxCnt++;
                if (score!=maxScore && score==secondScore) secondCnt++;
            }

            if (maxCnt==1 && secondCnt==1) {
                for (String key : pointMap.keySet()) {
                    int score = pointMap.get(key);
                    if (score==maxScore) tempArr[countryMap.get(key)] = 1.0;
                    else if (score==secondScore) tempArr[countryMap.get(key)] = 1.0;
                    else tempArr[countryMap.get(key)] = 0.0;
                }
            } else if (maxCnt==1 && secondCnt>1) {
                for (String key : pointMap.keySet()) {
                    int score = pointMap.get(key);
                    if (score==maxScore) tempArr[countryMap.get(key)] = 1.0;
                    else if (score==secondScore) tempArr[countryMap.get(key)] = 1.0/(double)secondCnt;
                    else tempArr[countryMap.get(key)] = 0.0;
                }
            } else if (maxCnt>1) {
                for (String key : pointMap.keySet()) {
                    int score = pointMap.get(key);
                    if (score==maxScore) tempArr[countryMap.get(key)] = 2.0/(double)maxCnt;
                }
            }

            for (int i = 0;i<4;i++) {
                answers[i]+=probability*tempArr[i];
            }
        }
    }
}
