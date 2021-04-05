package solutions.greedy;

import java.util.LinkedList;
import java.util.Queue;

public class SkillTree {
    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (int i = 0;i< skill_trees.length;i++) {
            String sk = skill_trees[i];
            Queue<Character> tempQue = new LinkedList<>();
            for (int j = 0;j<skill.length();j++) {
                tempQue.add(skill.charAt(j));
            }
            boolean flag = true;
            for (int j = 0;j<sk.length();j++) {
                char c = sk.charAt(j);
                if (tempQue.contains(c)) {
                    if (tempQue.peek()!=c) {
                        flag = false;
                    } else {
                        tempQue.poll();
                    }
                }
            }
            if (flag) {
                answer++;
            }
        }

        return answer;
    }

    public static void main(String args[]) {
        solution("CBD",new String[]{"BACDE","CBADF","AECB","BDA"});
    }
}
