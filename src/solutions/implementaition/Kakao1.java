package solutions.implementaition;

import java.util.*;

public class Kakao1 {
    static class Date {
        int year;
        int month;
        int day;

        Date(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        Date getEndDate(int period) {

            int year = this.year;
            int month = this.month;
            int day = this.day;

            month = month + period;

            year = year + ((month-1)/12);

            month = (month%12==0) ? 12 : month%12;

            day--;
            if (day == 0) {
                month--;
                day = 28;
            }
            if (month == 0) month = 12;

            return new Date(year, month, day);
        }

        boolean isEnded(int year, int month, int day) {
            if (year > this.year) {
                return true;
            } else if (year < this.year) {
                return false;
            } else {
                if (month > this.month) {
                    return true;
                } else if (month < this.month) {
                    return false;
                } else {
                    if (day > this.day) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }

    }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};

        List<Integer> resultList = new ArrayList<>();

        Date[] dateInfo = new Date[privacies.length];
        Map<String, Integer> termMap = new HashMap<>();
         Date todayDate = new Date(Integer.parseInt(today.split("\\.")[0]),
                                 Integer.parseInt(today.split("\\.")[1]),
                                 Integer.parseInt(today.split("\\.")[2]));

        for (int i = 0;i<terms.length;i++) {
            String type = terms[i].split(" ")[0];
            int period = Integer.parseInt(terms[i].split(" ")[1]);

            termMap.put(type, period);
        }

        for (int i = 0;i<privacies.length;i++) {
            String dateStr = privacies[i].split(" ")[0];
            String type = privacies[i].split(" ")[1];

            int year = Integer.parseInt(dateStr.split("\\.")[0]);
            int month = Integer.parseInt(dateStr.split("\\.")[1]);
            int day = Integer.parseInt(dateStr.split("\\.")[2]);
            int period = termMap.get(type);

            Date joinDate = new Date(year, month, day);
            Date endDate = joinDate.getEndDate(period);

            if (endDate.isEnded(todayDate.year, todayDate.month, todayDate.day)) {
                resultList.add(i+1);
            }
        }

        answer = new int[resultList.size()];

        for (int i = 0;i<resultList.size();i++) {
            answer[i] = resultList.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        String today = "2022.05.19";
        String[] terms = new String[]{"A 6","B 12","C 3"};
        String[] p = new String[]{
                "2021.05.02 A",
                "2021.07.01 B",
                "2022.02.19 C",
                "2022.02.20 C"
        };
        int[] result = solution(today, terms, p);

        for (int a : result) {
            System.out.println(a);
        }
    }
}
