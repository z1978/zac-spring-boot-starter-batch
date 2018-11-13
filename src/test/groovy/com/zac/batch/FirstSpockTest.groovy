package com.zac.batch

import static org.junit.Assert.*

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import com.zac.batch.entity.Person
import com.zac.batch.repository.PersonRepository

import spock.lang.Specification

@DataJpaTest
class FirstSpockTest  extends Specification {

  @Autowired
  TestEntityManager entityManager
  @Autowired
  PersonRepository personRepository

  def "spring context loads for data jpa slice"() {
    given: "some existing books"
    entityManager.persist(new Person("aaa", "bbb"))
    entityManager.persist(new Person("ccc", "ddd"))

    expect: "the correct count is inside the repository"
    personRepository.count() == 2L
  }

  def setupSpec() {
    //テストクラス内で一度きりの初期化
  }

  def setup() {
    //テストケースごとの初期化
  }

  //@Unroll 書くならここに付ける
  def "何かのテスト"() {
    //    setup:
    //    //何かの初期化
    //
    //    expect:
    //    //何らかの期待値
    //
    //    when:
    //    //何らかの条件
    //
    //    then:
    //    //モックメソッドの呼び出し回数確認など
    //
    //    cleanup:
    //    //後始末(あれば)
  }

  def cleanup() {}

  def cleanupSpec() {}
}
