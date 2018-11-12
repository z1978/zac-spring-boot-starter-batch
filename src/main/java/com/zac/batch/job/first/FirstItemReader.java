package com.zac.batch.job.first;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.zac.batch.BatchConstants;
import com.zac.batch.dto.PersonDto;

@Component(BatchConstants.FIRST_JOB_ITEM_READER_ID)
public class FirstItemReader extends FlatFileItemReader<PersonDto> implements StepExecutionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(FirstItemReader.class);

	@Value("${csv.input.path}")
	protected String csvInputPath;

	public FirstItemReader() {
		super();
		
		setLinesToSkip(1);
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "firstName", "lastName" });

		BeanWrapperFieldSetMapper<PersonDto> fieldSetMapper = new BeanWrapperFieldSetMapper<PersonDto>();
		fieldSetMapper.setTargetType(PersonDto.class);

		DefaultLineMapper<PersonDto> lineMapper = new DefaultLineMapper<PersonDto>();
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		setLineMapper(lineMapper);
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// JobのExecutionContextによるStep間の引き継ぎ
		ExecutionContext ctx = stepExecution.getJobExecution().getExecutionContext();
        LOGGER.info("I am NEXT TASKLET! execution context set by previous step context is {}", ctx.get("message")); // 前Stepからの引き継ぎ
        System.out.println(ctx.get("message"));
        
		JobParameters jobParameters = stepExecution.getJobParameters();
		String filePath = jobParameters.getString("filePath");
		LOGGER.info("filePath = [{}].", filePath);

		// TODO
		URL url = getClass().getClassLoader().getResource("input/sample-data.csv");
		System.out.println(url.getPath().toString());
		FileSystemResource resource = new FileSystemResource(url.getPath().toString());
//		FileSystemResource resource = new FileSystemResource(filePath);
		setResource(resource);
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}
}
