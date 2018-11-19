package com.zac.batch.launch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

@Component
public class StepDecider implements JobExecutionDecider {
	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
//		ExecutionContext ec = jobExecution.getExecutionContext();

		System.out.println(stepExecution.getStepName());
		System.out.println(stepExecution.getStatus());
		System.out.println(stepExecution.getExitStatus());
//		// COMPLETED, STOPPED, FAILED, UNKNOWN
//		System.out.println(FlowExecutionStatus.COMPLETED.getName());
//		System.out.println(FlowExecutionStatus.STOPPED.getName());
//		System.out.println(FlowExecutionStatus.FAILED.getName());
//		System.out.println(FlowExecutionStatus.UNKNOWN.getName());

		if (stepExecution.getStatus().equals(BatchStatus.COMPLETED)) {
			return FlowExecutionStatus.COMPLETED;
		} else if (stepExecution.getStatus().equals(BatchStatus.FAILED)) {
			return FlowExecutionStatus.STOPPED;
		} else if (stepExecution.getStatus().equals(BatchStatus.FAILED)) {
			return FlowExecutionStatus.FAILED;
		} else {
			return FlowExecutionStatus.UNKNOWN;
		}
	}
}
