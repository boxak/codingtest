package solutions.implementaition;

import java.util.*;

public class DiscController {
    class Job {
        int jobNo;
        int require;
        int process;

        Job(int jobNo, int require, int process) {
            this.jobNo = jobNo;
            this.require = require;
            this.process = process;
        }

    }

    public int solution(int[][] jobs) {
        int answer = 0;
        int nowTime = 0;
        int startTime = 0;
        int finishCnt = 0;
        int timeSum = 0;
        Job curJob = null;
        boolean occupied = false;

        Queue<Job> jobQue = new LinkedList<>();

        ArrayList<Job> jobList = new ArrayList<>();

        for (int i = 0;i<jobs.length;i++) {
            jobList.add(new Job(i, jobs[i][0], jobs[i][1]));
        }

        Collections.sort(jobList, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Job job1 = (Job)o1;
                Job job2 = (Job)o2;
                if (job1.require == job2.require) {
                    return job1.process - job2.process;
                } else return job1.require - job2.require;
            }

        });

        Queue<Job> que = new LinkedList<>();

        for (Job job : jobList) {
            que.add(job);
        }

        while(finishCnt < jobs.length) {
            Iterator<Job> iter = que.iterator();

            while(iter.hasNext()) {
                Job job = iter.next();
                if (job.require <= nowTime) {
                    jobQue.add(job);
                    iter.remove();

                    ArrayList<Job> jobQueList = new ArrayList<>();

                    for (Job temp : jobQue) {
                        jobQueList.add(temp);
                    }

                    Collections.sort(jobQueList, new Comparator() {
                        @Override
                        public int compare(Object o1, Object o2) {
                            Job job1 = (Job)o1;
                            Job job2 = (Job)o2;
                            return job1.process - job2.process;
                        }
                    });

                    jobQue.clear();

                    for (Job temp : jobQueList) {
                        jobQue.add(temp);
                    }

                }
            }

            if (occupied && curJob.process == nowTime - startTime) {
                timeSum+=nowTime - curJob.require;
                curJob = null;
                occupied = false;
                finishCnt++;
            }

            if (!occupied && !jobQue.isEmpty()) {
                curJob = jobQue.poll();
                occupied = true;
                startTime = nowTime;
            }

            nowTime++;

        }

        answer = timeSum / jobs.length;

        return answer;
    }
}
