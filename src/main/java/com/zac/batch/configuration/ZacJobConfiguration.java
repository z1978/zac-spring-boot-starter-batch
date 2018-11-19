package com.zac.batch.configuration;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zac.batch.BatchConstants;
import com.zac.batch.dto.PersonDto;
import com.zac.batch.entity.Person;
import com.zac.batch.launch.StepDecider;
import com.zac.batch.listener.JobStartEndListener;
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
	
	@Autowired
	private JobStartEndListener jobStartEndListener;

	@Bean
	public StepDecider decider1() {
		StepDecider decider = new StepDecider();
		return decider;
	}

	@Bean
	public StepDecider decider2() {
		StepDecider decider = new StepDecider();
		return decider;
	}

//	@Bean
//	public Job zacJob(Step step1, Step step2, Step step3) throws Exception {
//
//		LOGGER.info("step1 -> OK -> step2");
//		LOGGER.info("NG -> step3");
//		return jobBuilderFactory.get(BatchConstants.ZAC_JOB_ID)
//				.incrementer(new RunIdIncrementer())
//				.start(step1).on(ExitStatus.COMPLETED.getExitCode()).to(step2)
//                .from(step1).on(ExitStatus.FAILED.getExitCode()).to(step3)
//                .end()
//                .build();
//	
//	}

	@Bean
	public Job zacJob(Step step1, Step step2, Step step3) throws Exception {
		LOGGER.info("step1 -> OK -> step2");
		LOGGER.info("NG -> step3");
		return jobBuilderFactory.get(BatchConstants.ZAC_JOB_ID).incrementer(new RunIdIncrementer())
				.listener(jobStartEndListener)
//				.start(step1()).next(decider1()).on(FlowExecutionStatus.COMPLETED.getName()).to(step2())
//				.from(step1()).next(decider1()).on(FlowExecutionStatus.FAILED.getName()).to(step3())
//				.from(step2()).next(decider2()).on(FlowExecutionStatus.COMPLETED.getName()).to(step1())
				.start(step1()).on(FlowExecutionStatus.COMPLETED.getName()).to(step2())
				.from(step1()).on(FlowExecutionStatus.FAILED.getName()).to(step3())
				.start(step2()).on(FlowExecutionStatus.COMPLETED.getName()).to(step1())
				.from(step2()).on(FlowExecutionStatus.FAILED.getName()).to(step3())
				
//				.start(step1()).on(FlowExecutionStatus.COMPLETED.getName()).to(firstStep())
//				.from(step1()).on(FlowExecutionStatus.FAILED.getName()).to(step3())
//				.start(firstStep()).on(FlowExecutionStatus.COMPLETED.getName()).to(step1())
//				.from(firstStep()).on(FlowExecutionStatus.FAILED.getName()).to(step3())
				
//				.from(step1())
//				.next(decider())
//				.on(FlowExecutionStatus.FAILED.getName())
//				.to(step3())
//				.from(step2())
//				.next(decider())
//				.on(FlowExecutionStatus.FAILED.getName())
//				.to(step3())

//				.to(step2())
//				.next(decider())
//				.on(FlowExecutionStatus.COMPLETED.getName())
//				.to(step3())
//				.next(taskletNextStep())
//				.next(decider())
//				.on(FlowExecutionStatus.COMPLETED.getName())
//				.to(step1())
//				.from(step1())
//				.on(FlowExecutionStatus.FAILED.getName())
//				.to(step3())
//				.from(step2())
//				.on(FlowExecutionStatus.FAILED.getName())
//				.to(step3())
				.end().build();
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
		return stepBuilderFactory.get("firstStep").tasklet(firstTasklet()) // 上のTaskletをステップに登録
				.build();
	}

	@Bean
	protected Step taskletNextStep() {
		return stepBuilderFactory.get("nextStep").tasklet(nextTasklet()).build();
	}

	@Resource(name = BatchConstants.FIRST_JOB_ITEM_READER_ID)
	protected ItemReader<PersonDto> reader;

	@Resource(name = BatchConstants.FIRST_JOB_ITEM_PROCESSOR_ID)
	protected ItemProcessor<PersonDto, Person> processor;

	@Resource(name = BatchConstants.FIRST_JOB_ITEM_WRITER_ID)
	protected ItemWriter<Person> writer;

	@Bean
	public Step firstStep() throws Exception {
		return stepBuilderFactory.get(BatchConstants.FIRST_JOB_STEP_ID)
//				.partitioner(slaveStep().getName(), demoPartitioner)
//				.partitionHandler(handler())
				.repository(jobRepository)
				.<PersonDto, Person>chunk(chunckSize)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
}
