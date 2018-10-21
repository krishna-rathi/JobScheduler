import java.util.ArrayList;
import java.util.List;

import io.Job;
import service.JobSchedulerService;
import service.JobSchedulerServiceImpl;

public class Main {

	public static void main(String[] args) {
		
		// USERTYPEs
		// root->0
		// admin->1
		// user->2

		// Priority
		// P0-> 0
		// P1->1
		// P2->2
		System.out.println("JOB SCHEDULER");
		System.out.println("AUTHOR- Krishna Rathi (email-kkr.rathi@gmail.com");
		/** GIVEN TEST CASES START */
		JobSchedulerService jobScheduler = new JobSchedulerServiceImpl();
		Job job1 = new Job(1, 10, 0, 10, 0);
		Job job2 = new Job(2, 20, 0, 40, 1);
		Job job3 = new Job(3, 15, 2, 40, 0);
		Job job4 = new Job(4, 30, 1, 40, 2);
		Job job5 = new Job(5, 10, 2, 30, 2);

		List<Job> jobs = new ArrayList<>();
		jobs.add(job1);
		jobs.add(job2);
		jobs.add(job3);
		jobs.add(job4);
		jobs.add(job5);

		jobScheduler.shortestJobFirst(2, jobs);
		jobScheduler.earliestDeadlineFirst(2, jobs);
		/*jobScheduler.firstComeFirstServe(2, jobs);
		jobScheduler.fixedPriorityScheduling(2, jobs);
		*/
		/* GIVEN TEST CASES END */

	}

}
