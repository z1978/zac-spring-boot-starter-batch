package com.zac.batch.service.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class MailProperties {

	@Value("${mail.final.report.to}")
	private String[] to;

	@Value("${mail.final.report.from}")
	private String from;

	@Value("${mail.final.report.subject}")
	private String subject;
}