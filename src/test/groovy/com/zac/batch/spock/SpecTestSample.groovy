package com.zac.batch.spock

import static org.junit.Assert.*

import com.zac.batch.entity.Person
import com.zac.batch.repository.PersonRepository
import com.zac.batch.service.PersonService
import com.zac.batch.spock.AttractionRoom
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

import spock.lang.Specification

class SpecTestSample  extends Specification {

	def setupSpec() {
		// run before the first feature method
		// テストクラス内で一度きりの初期化
	}

	def setup() {
		// run before every feature method
		// テストケースごとの初期化
	}

	//@Unroll 書くならここに付ける
//	def "何かのテスト"() {
//		setup:
//		// 何かの初期化
//
//		expect:
//		// 何らかの期待値
//
//		when:
//		// 何らかの条件
//
//		then:
//		// モックメソッドの呼び出し回数確認など
//
//		cleanup:
//		//後始末(あれば)
//	}

	
	def cleanup() {
		// run after every feature method
	}

	def cleanupSpec() {
		// run after the last feature method
	}

}
