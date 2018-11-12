package com.zac.batch

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

import com.zac.batch.fortest.EmailAddress
import com.zac.batch.fortest.SocialMediaSite
import com.zac.batch.fortest.SocialMediaSiteRepository
import com.zac.batch.fortest.User

import spock.lang.Specification

@DataJpaTest
@ActiveProfiles("test")
public class SocialMediaSiteRepositoryTest extends Specification{


	@Autowired
	private SocialMediaSiteRepository socialMediaSiteRepository;

	def facebook = new SocialMediaSite("Facebook", "Online Social Media and Networking Service")


	def "find social media site FaceBook by Id" () {

		def savedFaceBookEntity  = socialMediaSiteRepository.save(facebook)

		when: "load facebook entity"
		def faceBookEntityFromDb = socialMediaSiteRepository.findOne(savedFaceBookEntity.getId())

		then:"saved and retrieved entity by id must be equal"
		savedFaceBookEntity.getId() == faceBookEntityFromDb.getId()
	}



	def "find social media site facebook by name"() {

		def savedFaceBookEntity  = socialMediaSiteRepository.save(facebook)

		when: "find by name FaceBook"
		def socialMediaEntity = socialMediaSiteRepository.findByName("Facebook")

		then: "saved and retrieved entity by name must be equal"
		socialMediaEntity == savedFaceBookEntity
	}




	def "add users to social mediate site  users " () {

		setup:

		def firstUser = new User("Mar", "Zuckerber", new EmailAddress("mark@mark.com"));
		facebook.addUser(firstUser);
		def savedFaceBookEntityWithUsers  = socialMediaSiteRepository.save(facebook)

		when:

		def faceBookEntityFromDB = socialMediaSiteRepository.findOne(savedFaceBookEntityWithUsers.getId())

		then:

		faceBookEntityFromDB.getUsers() != null
		faceBookEntityFromDB.getUsers().size() == 1;
	}
}
