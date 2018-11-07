package com.zac.batch

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import com.zac.batch.entity.Person
import com.zac.batch.repository.PersonRepository

import spock.lang.Specification


@DataJpaTest
public class SocialMediaSiteRepositoryTest extends Specification{


	@Autowired
	private PersonRepository personRepository

	def facebook = new Person("aaa", "bbb")


	def "find social media site FaceBook by Id" () {

		def savedFaceBookEntity  = personRepository.save(facebook)

		when: "load facebook entity"
		def faceBookEntityFromDb = personRepository.findOne(savedFaceBookEntity.getPersonId())

		then:"saved and retrieved entity by id must be equal"
		savedFaceBookEntity.getPersonId() == 1
	}
}
