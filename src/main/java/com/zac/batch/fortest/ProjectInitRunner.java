//package com.zac.batch;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.commons.cli.CommandLine;
//import org.apache.commons.cli.DefaultParser;
//import org.apache.commons.cli.Options;
//import org.apache.commons.cli.ParseException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.batch.core.BatchStatus;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//
//@EnableAutoConfiguration
//@ComponentScan
//public class ProjectInitRunner implements CommandLineRunner {
//	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectInitRunner.class);
//	private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//
//	@Value("${csv.input.path}")
//	protected String csvInputPath;
//	
//	@Override
//	public void run(String... args) throws Exception {
//
//
//
//		// コマンドライン引数を定義する
//		Options options = new Options();
//		options.addOption("j", "jobNmae", true, "Run job name.");
//		options.addOption("i", "inputPath", true, "Input directory.");
//		options.addOption("o", "outputPath", true, "Output directory.");
//
//		// コマンドライン引数をパースする
//		CommandLine cl = null;
//		try {
//			cl = new DefaultParser().parse(options, args);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// オプションが指定されているかどうかを確認する
//		if (cl.hasOption("j")) {
//			// オプション付きパラメタの受け取り
//			String jobName = cl.getOptionValue("j");
//			System.out.println(jobName);
//		}
//
//		if (cl.hasOption("i")) {
//			// オプション付きパラメタの受け取り
//			String intput = cl.getOptionValue("i");
//			System.out.println(intput);
//		}
//
//		if (cl.hasOption("o")) {
//			// オプション付きパラメタの受け取り
//			String output = cl.getOptionValue("o");
//			System.out.println(output);
//		}
//
//		if (cl.hasOption("z")) {
//			// オプション付きパラメタの受け取り
//			String output = cl.getOptionValue("z");
//			System.out.println(output);
//		}
//
//		// オプション無しパラメタの受け取り
//		// どちらもパラメタの内容自体は同じ
//		String[] params = cl.getArgs();
//		System.out.println(params[0]);
//		List<String> paramList = cl.getArgList();
//		System.out.println(paramList.size());
//
//		// ==================================================
//		if ((cl != null) && (cl.hasOption("j") && cl.hasOption("i"))) {
//			// オプション付きパラメタの受け取り
//			String jobName = cl.getOptionValue("j");
//			System.out.println(jobName);
//			String intputFileNmae = cl.getOptionValue("i");
//			System.out.println(intputFileNmae);
//
////			JobLauncher jobLauncher = (JobLauncher) context.getBean(JobLauncher.class);
//
//			if (jobName.equals(BatchConstants.FIRST_JOB_ID)
//					|| jobName.equals(BatchConstants.STRESS_TESTING_JOB_ID)) {
//				System.out.println("Job name is ok.");
//			} else {
//				System.out.println("########## Parameter ERROR !!!!!");
//				return;
//			}
//
////			Job runJobName = null;
////			try {
////				runJobName = (Job) context.getBean(jobName);
////			} catch (BeansException ex) {
////				LOGGER.error("A BeansException has occurred", ex);
////			}
//			JobParametersBuilder jpBuilder = new JobParametersBuilder();
//			jpBuilder.addString("time", DF.format(new Date()));
//
//			String intputFilePath = csvInputPath + intputFileNmae;
//			jpBuilder.addString("filePath", intputFilePath);
//
//			// TODO
//			jpBuilder.addString("param1", "GO_TO_FLOW_2");
//
//			JobParameters jobParameters = jpBuilder.toJobParameters();
//
//			try {
//				LOGGER.info("-- Run {} - Debut -----------------------------------------------");
//				//jobLauncher.run(runJobName, jobParameters);
//				
//				
//				SpringApplication app = new SpringApplication(ZacApplication.class);
//			    //app.setWebEnvironment(false);
//			    ConfigurableApplicationContext ctx = app.run(args);
//
//			    JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
//			    Job job = ctx.getBean(jobName, Job.class);
//			    //JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
//
//
//			    JobExecution jobExecution = jobLauncher.run(job, jobParameters);
//			    BatchStatus batchStatus = jobExecution.getStatus();
//			    System.out.println(batchStatus);
//				
//				
//				
//				
//				
//				
//				
//				
//				
//				
//				
//				
//				LOGGER.info("-- Run {} - Fin -------------------------------------------------");
//			} catch (JobExecutionAlreadyRunningException ex) {
//				LOGGER.error("A JobExecutionAlreadyRunningException has occurred", ex);
//			} catch (JobRestartException ex) {
//				LOGGER.error("A JobRestartException has occurred", ex);
//			} catch (JobInstanceAlreadyCompleteException ex) {
//				LOGGER.error("A JobInstanceAlreadyCompleteException has occurred", ex);
//			} catch (JobParametersInvalidException ex) {
//				LOGGER.error("A JobParametersInvalidException has occurred", ex);
//			} catch (Exception ex) {
//				LOGGER.error("An Exception has occurred", ex);
//			}
//		}
//	
//
//	}
//
//}
