package com.zac.batch.spock;

/**
 * 人を表すクラス。 年齢と性別を属性として持つ。
 */
public class Person {
	/** 性別("m" or "f") */
	private String sex;
	/** 年齢 */
	private int age;

	public Person(String sex, int age) {
		this.sex = sex;
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
