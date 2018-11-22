package com.zac.batch.service.mail

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource

import spock.lang.Specification


@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class MailServiceTest extends Specification{

	@Autowired
	private MailSender mailSender;

	@Autowired
	private MailProperties mailProperties

	def "test check mail properties after spring mapping"() {
		when:
		def to = mailProperties.getTo()
		def from = mailProperties.getFrom()

		then:
		to.length == 2
		to[0] == "test-to-1@mail.zac.com"
		to[1] == "test-to-2@mail.zac.com"

		from == "test-from@mail.zac.com"
	}

	def "test send mock"() {
		given:
		MailService service = new MailService()
		MailSender mockSender = Mock(MailSender)
		mockSender.send(_) >> {}

		service.mailSender = mockSender

		def testSubject = "[TEST] this is subject."
		def testText = "This is test contents."

		SimpleMailMessage sentMsg = new SimpleMailMessage();
		sentMsg.setTo(mailProperties.getTo())
		sentMsg.setFrom(mailProperties.getFrom())
		sentMsg.setSubject(testSubject)
		sentMsg.setText(testText)

		when:
		MailDto mailDto = new MailDto()
		mailDto.setTo(mailProperties.getTo())
		mailDto.setFrom(mailProperties.getFrom())
		mailDto.setSubject(testSubject)
		mailDto.setText(testText)

		service.send(mailDto)

		then:
		1 * mockSender.send(sentMsg)
	}

	def "test send error when invalid mail server host"() {
		given:
		MailService service = new MailService()
		service.mailSender = mailSender
		def invalidHost = "stmp.invalid.com"
		def originHost = ((JavaMailSenderImpl)service.mailSender).getHost()
		((JavaMailSenderImpl)service.mailSender).setHost(invalidHost)

		when:
		def testSubject = "[TEST] this is subject."
		def testText = "This is test contents."

		MailDto mailDto = new MailDto()
		mailDto.setTo(mailProperties.getTo())
		mailDto.setFrom(mailProperties.getFrom())
		mailDto.setSubject(testSubject)
		mailDto.setText(testText)

		service.send(mailDto)

		then:
		def ex = thrown(MailException)
		ex.getMessage().contains("Mail server connection failed; nested exception is javax.mail.MessagingException: Could not connect to SMTP host: ${invalidHost}, port: 25;")
		((JavaMailSenderImpl)service.mailSender).setHost(originHost)
	}

	def "test send error when invalid mail server port"() {
		given:
		MailService service = new MailService()
		service.mailSender = mailSender
		def invalidPort = 122
		def originPort = ((JavaMailSenderImpl)service.mailSender).getPort()
		((JavaMailSenderImpl)service.mailSender).setPort(invalidPort)

		when:
		def testSubject = "[TEST] this is subject."
		def testText = "This is test contents."

		MailDto mailDto = new MailDto()
		mailDto.setTo(mailProperties.getTo())
		mailDto.setFrom(mailProperties.getFrom())
		mailDto.setSubject(testSubject)
		mailDto.setText(testText)

		service.send(mailDto)

		then:
		def ex = thrown(MailException)
		ex.getMessage().contains("Mail server connection failed; nested exception is javax.mail.MessagingException: Could not connect to SMTP host: localhost, port: ${invalidPort};")
		((JavaMailSenderImpl)service.mailSender).setPort(originPort)
	}
}