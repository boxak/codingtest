package solutions.greedy;

public class SkillTree {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String skills : skill_trees) {
            int skillLen = skill.length();
            int skillsLen = skills.length();
            boolean checkSkill[] = new boolean[skillLen];
            boolean isPossible = true;
            for (int i = 0; i < skillsLen; i++) {
                for (int j = 0; j < skillLen; j++) {
                    char skill_A = skills.charAt(i);
                    char skill_B = skill.charAt(j);
                    if (skill_A == skill_B) {
                        if (j == 0) {
                            checkSkill[j] = true;
                        } else {
                            if (!checkSkill[j-1]) {
                                isPossible = false;
                                break;
                            } else {
                                checkSkill[j] = true;
                            }
                        }
                    }
                }
                if (!isPossible) {
                    break;
                }
            }
            if (isPossible) {
                answer++;
            }
        }

        return answer;
    }
}
