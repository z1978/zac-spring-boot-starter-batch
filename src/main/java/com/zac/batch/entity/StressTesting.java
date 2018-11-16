package com.zac.batch.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class StressTesting implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long testId;

	private String testPoint;

	private String testDate;

	public StressTesting() {
	}

	public StressTesting(String testPoint, String testDate) {
		this.testPoint = testPoint;
		this.testDate = testDate;
	}

}
