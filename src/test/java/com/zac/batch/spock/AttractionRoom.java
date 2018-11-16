package com.zac.batch.spock;

/**
 * アトラクションに人を載せたり降ろしたりするクラス。 載せる人の年齢や性別によって、アトラクションのキャパシティを計算していく
 */
public class AttractionRoom {
	private PersonChecker personChecker;

	public void setPersonChecker(PersonChecker personChecker) {
		this.personChecker = personChecker;
	}

	private CapacityCounter capacityCounter;

	public void setCapacityCounter(CapacityCounter capacityCounter) {
		this.capacityCounter = capacityCounter;
	}

	private int capacityLimit = 20;

	public void setCapacityLimit(int capacityLimit) {
		this.capacityLimit = capacityLimit;
	}

	/**
	 * 指定された人をアトラクションに追加する。 もし追加したらキャパシティオーバーになってしまう場合は、
	 * IllegalArgumentExceptionがスローされる
	 * 
	 * @param person 追加する人
	 * @throws java.lang.IllegalArgumentException 追加したらキャパシティオーバーになってしまう場合
	 */
	public void add(Person person) throws IllegalArgumentException {
		if (person == null) {
			throw new IllegalArgumentException("nullは許可されていません");
		}

		int add = 0;
		if (!personChecker.isAdult(person)) {
			// 子どもは男女とも1
			add = 1;
		} else if (personChecker.isMale(person)) {
			// 大人で男性の場合は3
			add = 3;
		} else {
			// 大人で女性の場合は2
			add = 2;
		}

		// この人を乗せるとキャパシティオーバーになる場合は例外をスロー
		if (capacityCounter.getCount() > (capacityLimit - add)) {
			throw new IllegalArgumentException("limit over");
		}

		capacityCounter.addCount(add);
	}

	/**
	 * 現在までのキャパシティのカウントを返します。
	 * 
	 * @return 現在までのキャパシティカウント
	 */
	public int getCount() {
		return this.capacityCounter.getCount();
	}
}
