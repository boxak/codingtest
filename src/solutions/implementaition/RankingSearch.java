package solutions.implementaition;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RankingSearch {
    public static int[] solution(String[] infos, String[] querys) {
        int[] answer = {};

        String[] langs = new String[]{"-", "cpp","java","python"};
        String[] jobs = new String[]{"-","backend","frontend"};
        String[] careers = new String[]{"-","junior","senior"};
        String[] foods = new String[]{"-","pizza","chicken"};

        HashMap<String, ArrayList<Integer>> conditionMap = new HashMap<>();

        for (String lang : langs) {
            for (String job : jobs) {
                for (String career : careers) {
                    for (String food : foods) {
                        String regex = lang+" "+job+" "+career+" "+food;
                        String numRegex = "[0-9]{1,}";

                        regex = regex.replaceAll("-","[a-z]{1,}");

                        regex = regex + " [0-9]{1,}";

                        String key = lang+" and "+job+" and "+career+" and "+food;

                        Pattern pattern = Pattern.compile(regex);
                        Pattern numPattern = Pattern.compile(numRegex);
                        ArrayList<Integer> numList = new ArrayList<>();

                        for (String info : infos) {
                            Matcher matcher = pattern.matcher(info);
                            if (matcher.find()) {
                                Matcher numMatcher = numPattern.matcher(info);
                                if (numMatcher.find()) {
                                    String numStr = numMatcher.group();
                                    numList.add(Integer.parseInt(numStr));
                                }
                            }
                        }

                        Collections.sort(numList);

                        conditionMap.put(key, numList);
                    }
                }
            }
        }

        Pattern numPattern = Pattern.compile("[0-9]{1,}");

        ArrayList<Integer> resultList = new ArrayList<>();

        for (String query : querys) {
            Matcher matcher = numPattern.matcher(query);
            int score = 0;
            if (matcher.find()) {
                String str = matcher.group();
                score = Integer.parseInt(str);
                String key = query.replace(" "+str,"");
                ArrayList<Integer> rankings = conditionMap.get(key);
                if (rankings != null && !rankings.isEmpty()) {
                    if (rankings.get(rankings.size()-1) < score) {
                        resultList.add(0);
                    } else {
                        int index = binarySearch(rankings, score, 0, rankings.size() - 1);
                        resultList.add(rankings.size() - index);
                    }
                } else {
                    resultList.add(0);
                }
            }
        }

        answer = new int[resultList.size()];

        for (int i = 0;i< resultList.size();i++) {
            answer[i] = resultList.get(i);
        }

        return answer;
    }

    static int binarySearch(ArrayList<Integer> list,int target, int s, int e) {
        if (s>=e) {
            if (list.get(s) == target) {
                return s;
            } else return e;
        }

        int mid = (s+e)/2;
        if (list.get(mid) == target) {
            return mid;
        } else if (list.get(mid) > target) {
            return binarySearch(list, target, s, mid);
        } else {
            return binarySearch(list, target, mid+1, e);
        }

    }

    public static void main(String[] args) {
        String[] strArr = new String[]{
                "java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"
        };

        String[] query = new String[]{
                "java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"
        };

        int[] arr = solution(strArr, query);

        for (int a : arr) {
            System.out.printf("%d ",a);
        }

//        ArrayList<Integer> list = new ArrayList<>();
//
//        list.add(10);
//        list.add(20);
//        list.add(87);
//        list.add(93);
//        list.add(100);
//        //10 20 87 93 100
//
//        int index = binarySearch(list, 110, 0, 4);
//
//        System.out.println(index);

    }
}
