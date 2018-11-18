package com.zac.batch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ZacApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZacApplication.class);

	private static final DateFormat DF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	@Value("${csv.input.path}")
	protected String csvInputPath;

	public static void main(String[] args) {
		LOGGER.info("Application main START==================================================");
		long startTime = System.currentTimeMillis();
		LOGGER.info("Start time = [" + DF.format(new Date()) + "]");
		LOGGER.info("Application main START==================================================");
		try {
			SpringApplication.exit(SpringApplication.run(ZacApplication.class, args));
		} catch (Exception ex) {
			LOGGER.error("critical error!!", ex);
			System.out.println("致命的なエラーが発生しました。詳細はログを確認して下さい。");
			System.exit(1);
		}
		System.out.println("Application main END==================================================");
		LOGGER.info("End time = [" + DF.format(new Date()) + "]");
		long endTime = System.currentTimeMillis();
		LOGGER.info("Program running time = [" + (endTime - startTime) + "]ms");
		System.out.println("Program running time = [" + (endTime - startTime) / 1000f + "]s");
		LOGGER.info("Program running time = [" + (endTime - startTime) / 1000f + "]s");
		System.out.println("Application main END==================================================");
	}

}
