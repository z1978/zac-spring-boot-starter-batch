package com.zac.batch;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.zac.batch.fortest.EmailAddress;
import com.zac.batch.fortest.SocialMediaSite;
import com.zac.batch.fortest.SocialMediaSiteRepository;
import com.zac.batch.fortest.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@DataJpaTest
public class AppTest {
	@Autowired
	private SocialMediaSiteRepository socialMediaSiteRepository;

	private SocialMediaSite facebook;

	@Before
	public void setUp() {
		facebook = new SocialMediaSite("Facebook", "Online Social Media and Networking Service");

	}

	@Test
	public void saveFacebookAndFindById() {
		facebook = socialMediaSiteRepository.save(facebook);
		assertThat(socialMediaSiteRepository.findById(facebook.getId())).isEqualTo((facebook));

	}

	@Test
	public void saveFacebookAndFindBySocialMediaSiteName() {
		facebook = socialMediaSiteRepository.save(facebook);
		assertThat(socialMediaSiteRepository.findByName("Facebook")).isEqualTo((facebook));
	}

	@Test
	public void saveFacebookUsers() {

		User firstUser = new User("Mar", "Zuckerber", new EmailAddress("mark@mark.com"));
		facebook.addUser(firstUser);

		facebook = socialMediaSiteRepository.save(facebook);
		
		assertThat(socialMediaSiteRepository.findById(facebook.getId()));

		// user added is not null
//		assertThat(socialMediaSiteRepository.findOne(facebook.getId()).getUsers()).isNotNull();

		// check the one user we added.
//		assertThat(socialMediaSiteRepository.findOne(facebook.getId()).getUsers().size()).isEqualTo(1);

	}
}