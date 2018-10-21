package service;

import io.EDF;
import io.Job;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class JobSchedulerServiceImpl implements JobSchedulerService{
		
	@Override
	public void shortestJobFirst(int threadNo, List<Job> job)
	{
		System.out.println("SJF:");
		List<Job> jobs = job;
		jobs.sort(new Comparator<Job>() {
			@Override
			public int compare(Job j1, Job j2) {
				if (j1.getduration() == j2.getduration()) {
					return j1.getpriority() - j2.getpriority();
				}
				return (int) (j1.getduration() - j2.getduration());
			}
		});

		Map<Integer, List<Integer>> threads = assignThreadsToJobs(threadNo, jobs);
		displayScheduledJobs(threads);
	}
	
	@Override
	public void earliestDeadlineFirst(int threadNo, List<Job> job) {

		System.out.println("EDF:");
		List<Job> jobs = job;
		jobs.sort(new Comparator<Job>() {
			@Override
			public int compare(Job j1, Job j2) {
				if (j1.getdeadline() == j2.getdeadline()) {
					if (j1.getpriority() == j2.getpriority()) {
						return (int) (j1.getduration() - j2.getduration());
					} else {
						return j1.getpriority() - j2.getpriority();
					}
				}
				return (int) (j1.getdeadline() - j2.getdeadline());
			}
		});
		Map<Integer, EDF> threads = assignThreadsToJobsForEdf(threadNo, jobs);
		displayScheduledJobsForEdf(threads);

	}

	private void displayScheduledJobsForEdf(Map<Integer, EDF> threads) {
		for (Map.Entry<Integer, EDF> entry : threads.entrySet()) {
			System.out.print("Thread:" + entry.getKey() + " - ");
			entry.getValue().getJobIDs().forEach(name -> {
				System.out.print(name + " ");
			});
			System.out.println();
		}

	}

	private Map<Integer, EDF> assignThreadsToJobsForEdf(int threadNo, List<Job> jobs) {
		Map<Integer, EDF> threads = new HashMap();
		EDF edf;
		int deadline = 0;
		int thread = 0;
		for (Job j : jobs) {
			if (!threads.containsKey(thread % threadNo)) {
				if (j.getduration() <= j.getdeadline()) {
					edf = new EDF();
					edf.setJobIDs(new ArrayList<Integer>());
					edf.getJobIDs().add(j.getjobID());
					edf.setDeadline(j.getduration());
					threads.put(thread % threadNo, edf);
					thread++;
				}
			} else {
				edf = threads.get(thread % threadNo);
				if (edf.getDeadline() + j.getduration() <= j.getdeadline()) {
					edf.setDeadline(edf.getDeadline() + j.getduration());
					edf.getJobIDs().add(j.getjobID());
					thread++;
				}
			}

		}
		return threads;

	}

	private Map<Integer, List<Integer>> assignThreadsToJobs(int threadNo, List<Job> job) {
		
		Map<Integer, List<Integer>> threads = new HashMap();
		List<Integer> jobNames;
		int thread=0;
		for(Job j : job) {
			if(threads.containsKey(thread%threadNo))
			{
				jobNames = threads.get(thread%threadNo);
				jobNames.add(j.getjobID());
			}
			else
			{
				jobNames = new ArrayList<>();
				jobNames.add(j.getjobID());
				threads.put(thread%threadNo, jobNames);
			}
			thread++;
		}
		return threads;
	}

	private void displayScheduledJobs(Map<Integer, List<Integer>> threads) {
		
		for (Entry<Integer, List<Integer>> entry : threads.entrySet()) {
			System.out.print("Thread:" + entry.getKey() + " - ");
			entry.getValue().forEach(name -> {
				System.out.print(name + " ");
			});
			System.out.println();
		}
		
	}
	
}
