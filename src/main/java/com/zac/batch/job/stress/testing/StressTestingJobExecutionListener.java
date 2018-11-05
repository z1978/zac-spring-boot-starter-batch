package com.zac.batch.job.stress.testing;

import java.util.Collection;
import java.util.Date;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zac.batch.BatchConstants;

@Component(BatchConstants.STRESS_TESTING_JOB_EXECUTION_LISTENER_ID)
public class StressTestingJobExecutionListener extends JobExecutionListenerSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(StressTestingJobExecutionListener.class);

	private final JdbcTemplate jdbcTemplate;

	public StressTestingJobExecutionListener(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		ExecutionContext ec = jobExecution.getExecutionContext();
		System.out.println(ec.get("message"));
		
		Collection<StepExecution> setpEx = jobExecution.getStepExecutions();
		System.out.println(setpEx.size());
		for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
			System.out.println(stepExecution.getStepName() + " "
					+ stepExecution.getReadCount() + " "
					+ stepExecution.getFilterCount() + " "
					+ stepExecution.getWriteCount() + " " + stepExecution.getCommitCount());
		}
		String jobName = jobExecution.getJobInstance().getJobName();
		Date jobStartTime = jobExecution.getStartTime();
		Date jobEndTime = jobExecution.getEndTime();
		BatchStatus jobBatchStatus = jobExecution.getStatus();
		String jobExitCode = jobExecution.getExitStatus().getExitCode();
		// 日付をlong値に変換します。
	    long dateTimeTo = jobEndTime.getTime();
	    long dateTimeFrom = jobStartTime.getTime();
	 
	    // 差分の日数を算出します。
	    long dayDiff = dateTimeTo - dateTimeFrom;//(1000 * 60 * 60 * 24 );
	    System.out.println(jobName);
	    System.out.println(jobBatchStatus);
	    System.out.println(jobExitCode);
	    System.out.println("Program running time = [" + dayDiff / 1000f + "]s");
	    LOGGER.info("Program running time = [" + dayDiff / 1000f + "]s");
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOGGER.info("!!! JOB FINISHED! Time to verify the results");
		}
	}
}
