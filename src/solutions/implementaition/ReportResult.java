package solutions.implementaition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReportResult {

    public int[] solution(String[] id_list, String[] reports, int k) {
        int[] answer = {};
        ArrayList<Integer> answerList = new ArrayList<>();
        Map<String, Integer> reportMap = new HashMap<>();
        Map<String, ArrayList<String>> reportListMap = new HashMap<>();
        ArrayList<String> reportList = new ArrayList<>();

        for (String id : id_list) {
            reportMap.put(id, 0);
            reportListMap.put(id, new ArrayList<>());
        }

        for (String report : reports) {
            String reporterId = report.split(" ")[0];
            String reportUserId = report.split(" ")[1];

            int reportCnt = reportMap.get(reportUserId);
            if (!reportListMap.get(reporterId).contains(reportUserId)) {
                reportListMap.get(reporterId).add(reportUserId);
                reportMap.put(reportUserId, reportCnt + 1);
            }
        }

        for (String id : id_list) {
            int cnt = 0;
            for (String reportId : reportListMap.get(id)) {
                if (reportMap.get(reportId) >= k) {
                    cnt++;
                }
            }
            answerList.add(cnt);
        }

        answer = new int[answerList.size()];

        for (int i = 0;i<answerList.size();i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

}
