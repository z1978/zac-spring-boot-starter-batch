package com.zac.batch.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class PersonDto {

	@NotNull
	@Range(min = 1, max = 10)
	private String lastName;
	@NotNull
	@Range(min = 1, max = 10)
	private String firstName;

	public PersonDto() {
	}

	public PersonDto(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "firstName: " + firstName + ", lastName: " + lastName;
	}

}