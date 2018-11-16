package com.zac.batch.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class StressTestingDto {

	@NotNull
	@Range(min = 1, max = 10)
	private Integer testId;
	private String testPoint;
	private String testDate;

	public StressTestingDto() {
	}

	public StressTestingDto(Integer testId, String testPoint, String testDate) {
		this.testId = testId;
		this.testPoint = testPoint;
		this.testDate = testDate;
	}

	@Override
	public String toString() {
		return "testId: " + testId + ", testPoint: " + testPoint + ", testDate: " + testDate;
	}

}