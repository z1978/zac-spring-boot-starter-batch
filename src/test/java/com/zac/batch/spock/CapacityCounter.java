package com.zac.batch.spock;

/**
 * キャパシティを数値で管理するクラス。
 */
public class CapacityCounter {
	private int count = 0;

	public CapacityCounter() {
	}

	/**
	 * 指定された数だけカウントを加算する
	 * 
	 * @param count 加算するカウント
	 */
	public void addCount(int count) {
		this.count += count;
	}

	/**
	 * 指定された数だけカウントを減算する
	 * 
	 * @param count 減算するカウント
	 */
	public void reduceCount(int count) {
		this.count -= count;
	}

	/**
	 * 現在のカウントを取得する
	 * 
	 * @return 現在のカウント
	 */
	public int getCount() {
		return this.count;
	}
}
