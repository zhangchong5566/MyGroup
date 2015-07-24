package com.zhc.schedule.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		System.out.println("Starting job");
		
		
		System.out.println("Shutdown job");
	}

}
