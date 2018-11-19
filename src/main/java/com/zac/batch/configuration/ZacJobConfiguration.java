package com.zac.batch.configuration;

import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zac.batch.BatchConstants;
import com.zac.batch.task.FirstTasklet;
import com.zac.batch.task.NextTasklet;
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
	public Job zacJob(Step step1, Step step2, Step step3, Step taskletFirstStep, Step taskletNextStep) throws Exception {
		LOGGER.info("step1 -> OK -> step2");
		LOGGER.info("NG -> step3");
		return jobBuilderFactory.get(BatchConstants.ZAC_JOB_ID).incrementer(new RunIdIncrementer())
				.start(step1)
				.on(ExitStatus.COMPLETED.getExitCode())
				.to(step2)
				.next(taskletFirstStep)
				.next(taskletNextStep)
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

	/**
     * Taskletの定義.
     *
     * @return FirstTasklet
     */
    @Bean
    public Tasklet firstTasklet() {
        return new FirstTasklet();
    }


    /**
     * 後続Taskletの定義
     *
     * @return NextTasklet
     */
    @Bean
    public Tasklet nextTasklet() {
        return new NextTasklet();
    }

    /**
     * ジョブステップの定義.
     *
     * @return firstStep
     */
    @Bean
    protected Step taskletFirstStep() {
        return stepBuilderFactory.get("firstStep")
                .tasklet(firstTasklet()) // 上のTaskletをステップに登録
                .build();
    }

    @Bean
    protected  Step taskletNextStep() {
        return stepBuilderFactory.get("nextStep")
                .tasklet(nextTasklet())
                .build();
    }

}
