package com.zac.batch.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zac.batch.entity.Person;
import com.zac.batch.repository.PersonRepository;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class PersonService {
	final static Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	private PersonRepository personRepository;

	public void findOne() {
		String sql;
		try {
			sql = IOUtils.toString(
			        this.getClass().getClassLoader().getResourceAsStream("sql/find.sql"));
			System.out.println(sql);
			Query query = em.createNativeQuery(sql);
			List<Object[]> rows = query.getResultList();
			for (Object[] row : rows){
				System.out.println(row[0]);
				System.out.println(row[1]);
				System.out.println(row[2]);
				System.out.println("=====");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		    
	}

	public Person findById(int id) {
		return personRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Person> getAll() {
		return personRepository.findAll();

	}

	@Transactional
	public void save(Person person) {
		personRepository.save(person);
	}

	// 外部からこのメソッドを呼び出した場合、saveで例外が発生してもロールバックされない
	public void save2(Person entity) {
		save(entity);
	}

	@Transactional
	public Person saveAndFlush(Person person) {
		if (person != null) {
			person = personRepository.saveAndFlush(person);
		}

		return person;
	}

	@Transactional
	public void deleteAll() {
		personRepository.deleteAll();
	}

//	public PersonService(PersonRepository personRepository) {
//		this.personRepository = personRepository;
//	}
//
//	public List<Person> findAll() {
//		return personRepository.findAll();
//	}
//
//	//@Modifying
//	public void deleteAll() {
//		personRepository.deleteAll();
//	}
//
	// @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Transactional
	public void createOne(Person person) {
		personRepository.save(person);
	}
//
//    //@Transactional(readOnly = false)
//	public void deleteById(Integer id) {
//		personRepository.deleteById(id);
//	}
//    
//	public void saveOne(Person person) {
//		personRepository.saveAndFlush(person);
//	}

}
