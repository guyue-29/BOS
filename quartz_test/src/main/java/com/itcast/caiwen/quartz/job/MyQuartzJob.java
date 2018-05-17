package com.itcast.caiwen.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.itcast.caiwen.quartz.service.MyQuartzService;

public class MyQuartzJob implements Job {

	@Autowired
	private MyQuartzService myQuartzService;

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		myQuartzService.sayHello();
	}

}
