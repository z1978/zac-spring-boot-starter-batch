package com.zac.batch;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.zac.batch.entity.Person;
import com.zac.batch.repository.PersonRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationTest {
	@Autowired
	private PersonRepository personRepository;

	private Person person;
	
	@Before
	public void setUp() {
		person = new Person();
		person.setPersonId(1);
		person.setFirstName("a");
		person.setLastName("b");

	}
	
	
	@Test
	public void saveFacebookAndFindById() {
		person = personRepository.save(person);
		assertThat(personRepository.findById(person.getPersonId())).isEqualTo((person));

	}

}
