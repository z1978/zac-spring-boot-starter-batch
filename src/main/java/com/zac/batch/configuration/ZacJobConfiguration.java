package com.zac.batch.configuration;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zac.batch.BatchConstants;
import com.zac.batch.task.Task1;
import com.zac.batch.task.Task2;
import com.zac.batch.task.Task3;

@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
public class ZacJobConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZacJobConfiguration.class);

	@Value("${first.chunck.size}")
	protected Integer chunckSize;

	@Autowired
	protected JobBuilderFactory jobBuilderFactory;

	@Autowired
	protected StepBuilderFactory stepBuilderFactory;

	@Autowired
	protected EntityManagerFactory entityManagerFactory;

	@Autowired
	protected JobRepository jobRepository;

	@Autowired
	private Task1 task1;

	@Autowired
	private Task2 task2;

	@Autowired
	private Task3 task3;

	@Bean
	public Job zacJob(Step step1, Step step2, Step step3) throws Exception {
		LOGGER.info("step1 -> OK -> step2");
		LOGGER.info("NG -> step3");
		return jobBuilderFactory.get(BatchConstants.ZAC_JOB_ID)
				.incrementer(new RunIdIncrementer())
				.start(step1)
				.on(ExitStatus.COMPLETED.getExitCode())
				.to(step2)
				.from(step1)
				.on(ExitStatus.FAILED.getExitCode())
				.to(step3)
				.from(step2)
				.on(ExitStatus.COMPLETED.getExitCode())
				.to(step1)
				.from(step2)
				.on(ExitStatus.FAILED.getExitCode())
				.to(step3)
				.end()
				.build();
	}

	@Bean
	public Step step1() {
		LOGGER.info("step1");
		return stepBuilderFactory.get("step1").tasklet(task1).build();
	}

	@Bean
	public Step step2() {
		LOGGER.info("step2");
		return stepBuilderFactory.get("step2").tasklet(task2).build();
	}

	@Bean
	public Step step3() {
		LOGGER.info("step3");
		return stepBuilderFactory.get("step3").tasklet(task3).build();
	}

}
