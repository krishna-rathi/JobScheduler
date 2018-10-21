package io;

public class Job {
	private int jobID;
	private int duration;
	private int priority;
	private double deadline;
	private int userType;
	
	public Job()
	{
		
	}
	
	public Job(int jobID, int duration, int priority, double deadline, int userType)
	{
		this.jobID = jobID;
		
	}
	
	public int getjobID()
	{
		return jobID;
	}
	void setjobID(int jobID)
	{
		this.jobID = jobID;
	}
	public int getduration()
	{
		return duration;
	}
	void setduration(int duration)
	{
		this.duration = duration;
	}
	public int getpriority()
	{
		return priority;
	}
	void setpriority(int priority)
	{
		this.priority = priority;
	}
	public double getdeadline()
	{
		return deadline;
	}
	void setdeadline(double deadline)
	{
		this.deadline = deadline;
	}
	public int getuserType()
	{
		return userType;
	}
	void setuserType(int userType)
	{
		this.userType = userType;
	}

}
