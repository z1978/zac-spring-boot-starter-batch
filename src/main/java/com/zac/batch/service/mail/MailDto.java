package com.zac.batch.service.mail;

import lombok.Data;

@Data
public class MailDto {

	private String[] to;

	private String from;

	private String[] bcc;

	private String[] cc;

	private String subject;

	private String text;
}
