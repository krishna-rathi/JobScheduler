package service;

import java.util.List;

import io.Job;

public interface JobSchedulerService {

	void shortestJobFirst(int threadcount, List<Job> jobs);
	
	void earliestDeadlineFirst(int threadcount, List<Job> jobs);
}
