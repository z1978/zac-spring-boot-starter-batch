package com.zac.batch.job.first;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;

import com.zac.batch.BatchConstants;
import com.zac.batch.entity.Person;
import com.zac.batch.repository.PersonRepository;

@Component("firstCsvItemWriter")
public class FirstCsvItemWriter implements ItemWriter<Person> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FirstCsvItemWriter.class);
	
	@Autowired
	private PersonRepository personRepository;

//	public ItemWriter<Person> writer() throws IOException {
//		FlatFileItemWriter<Person> writer = new FlatFileItemWriter<Person>();
//		writer.setResource(new PathResource("test.csv"));
//		DelimitedLineAggregator<Person> delimitedLineAggregator = new DelimitedLineAggregator<Person>();
//		delimitedLineAggregator.setDelimiter(",");
//		String[] names = { "personId", "firstName", "lastName" };
//		BeanWrapperFieldExtractor<Person> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<Person>();
//		beanWrapperFieldExtractor.setNames(names);
//		delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
//		writer.setLineAggregator(delimitedLineAggregator);
//		return writer;
//	}

	@Override
	public void write(List<? extends Person> items) throws Exception {
		// TODO Auto-generated method stub
		FlatFileItemWriter<Person> writer = new FlatFileItemWriter<Person>();
		writer.setResource(new PathResource("test.csv"));
		DelimitedLineAggregator<Person> delimitedLineAggregator = new DelimitedLineAggregator<Person>();
		delimitedLineAggregator.setDelimiter(",");
		String[] names = { "personId", "firstName", "lastName" };
		BeanWrapperFieldExtractor<Person> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<Person>();
		beanWrapperFieldExtractor.setNames(names);
		delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
		writer.setLineAggregator(delimitedLineAggregator);
		
	}}
