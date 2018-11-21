package com.zac.batch.job.first;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

import com.zac.batch.BatchConstants;

public class FirstDecider implements JobExecutionDecider {
	private int count = 0;

	private int limit = 1;

	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		if (++count >= limit) {
			return new FlowExecutionStatus(BatchConstants.DECIDER_COMPLETED);
		} else {
			return new FlowExecutionStatus(BatchConstants.DECIDER_CONTINUE);
		}
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
