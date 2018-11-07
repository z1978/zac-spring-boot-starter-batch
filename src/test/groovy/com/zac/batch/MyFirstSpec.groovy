package com.zac.batch

import static org.junit.Assert.*

import com.zac.batch.entity.Person
import com.zac.batch.repository.PersonRepository
import com.zac.batch.service.PersonService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

import spock.lang.Specification

@DataJpaTest
class MyFirstSpec  extends Specification {

	PersonRepository personRepositoryMock = Mock()

	def "let's try this!"() {
		expect:
		Math.max(1, 3) == 3
	}

	def "test where"(){
		given:
		def service = new PersonService()
		Person person = new Person()
		person.setPersonId(1)
		person.setFirstName('aaa')
		person.setLastName('bbb')
		1 * personRepositoryMock.findById(1) >> person

		when:
		def result = service.findById(1)

		then:
		result.getFirstName() == 'aaa'
	}
	
	@Autowired
	TestEntityManager entityManager
	@Autowired
	PersonRepository personRepository
	
	def "spring context loads for data jpa slice"() {
		given: "some existing books"
		entityManager.persist(new Person("aaa", "bbb"))
		entityManager.persist(new Person("ccc", "ddd"))
	
		expect: "the correct count is inside the repository"
		personRepository.count() == 2L
	  }
}
