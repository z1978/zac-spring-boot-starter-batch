package com.zac.batch.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailService {

	@Autowired
	private MailSender mailSender;

	@Autowired
	private MailProperties mailProperties;

	public void send(MailDto mailDto) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(mailDto.getTo());
		msg.setFrom(mailDto.getFrom());

		String[] bcc = mailDto.getBcc();
		if (bcc != null && bcc.length > 0) {
			msg.setBcc(bcc);
		}

		String[] cc = mailDto.getCc();
		if (cc != null && cc.length > 0) {
			msg.setCc(cc);
		}

		msg.setSubject(mailDto.getSubject());
		msg.setText(mailDto.getText());

		mailSender.send(msg);
	}
}