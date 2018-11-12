package com.zac.batch.fortest;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EmailAddressConverter implements AttributeConverter<EmailAddress, String> {

	@Override
	public String convertToDatabaseColumn(EmailAddress emailAddress) {
		return emailAddress.toString();
	}

	@Override
	public EmailAddress convertToEntityAttribute(String email) {
		return new EmailAddress(email);
	}
}
