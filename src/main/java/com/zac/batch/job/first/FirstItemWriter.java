package com.zac.batch.job.first;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zac.batch.BatchConstants;
import com.zac.batch.entity.Person;
import com.zac.batch.repository.PersonRepository;
import com.zac.batch.service.PersonService;

@Component(BatchConstants.FIRST_JOB_ITEM_WRITER_ID)
public class FirstItemWriter implements ItemWriter<Person> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FirstItemWriter.class);

	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonRepository personRepository;

	public void write(List<? extends Person> items) throws Exception {
		personService.findOne();
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Writing to JPA with {} items.", items.size());
		}
        Person person3 = personRepository.save(new Person("user3", "female"));

		/* 登録情報の確認 */
        System.out.println("DB登録結果確認");
        personRepository.findAll().forEach(p -> System.out.println(p.toString()));


        /* 登録情報の変更 */
        person3.setFirstName("CCC");
        personRepository.save(person3);
        /* 登録情報変更の確認 */
        System.out.println("DB登録変更結果確認");
        personRepository.findAll().forEach(p -> System.out.println(p.toString()));
        List<Person> testList = personRepository.findByFirstName("CCC");
        System.out.println(testList.size());
		if (!items.isEmpty()) {
			List<Person> itemList = new ArrayList<>();
			Person person = new Person();
			person.setPersonId(991);
			person.setFirstName("XXX");
			person.setLastName("VVV");
			itemList.add(person);
			
			List<Person> list = this.personService.getAll();
			System.out.println(list.size());
			System.out.println("==========");			
			for (Person item : list) {
				itemList.add(item);
				System.out.println(item.getPersonId());
				System.out.println(item.getFirstName());
				System.out.println(item.getLastName());
			}
//			personRepository.deleteAll();
			personRepository.save(person);
			personRepository.saveAll(itemList);
			System.out.println("==========");
		}
	}
}
