# JPA에서 가장 중요한 2가지

1. 객체와 관계형 데이터베이스 매핑하기 (Object Relational Mapping)
2. 영속성 컨텍스트

## 영속성 컨텍스트

EMF와 EM

EMF가 EM 생성. EM이 conn생성

* 엔티티를 영구 저장하는 환경이라는 뜻.
* EmtityManager.persist(entity);

영속성 컨텍스트는 논리적인 개념이다. 

엔티티매니저를 통해 영속성 컨텍스트에 접근한다...

EntityManager > Persistence Context...

```java
//비영송
Member member = new Member();
member.setId(100L);
member.setName("helloJPA");

//영속 ( 객체를 저장한 상태)
em.persist(member);
```



영속성 컨텍스트의 이점

* 1차 캐시

  * ```java
    Member a = em.find(Member.class, "member1");
    Member a = em.find(Member.class, "member1");
    // 실행시 쿼리문이 한번만 나간다!
    ```

* 동일성(identity) 보장

  * ```java
    Member a = em.find(Member.class, "member1");
    Member b = em.find(Member.class, "member1");
    
    System.out.println(a==b); // true!!!
    ```

* 트랜잭션을 지원하는 쓰기 지연(transactional write-behind)

  * ```java
    Member member1 = new Member(150L, "A");
                Member member2 = new Member(160L, "B");
    
    em.persist(member1);
    em.persist(member2);
    
    이때 sql문이 실행되는 것이 아니라. 쓰기지연 sql에 저장되어 있는다. 그 후 commit되면 쿼리문이 한번에 실행된다.
    tx.commit();
    ```

* 변경 감지(Dirty Checking)

  * 수정되면 flush()시점에 반영된다.
    * em.flush() - 직접호출
    * 트랜잭션 커밋 - 플러시 자동 호출
    * JPQL 쿼리 실행 - 플러시 자동 호출

* 지연 로딩(Lazy Loading)



준영속 상태..

* 영속 상태의 엔티티가 영속성 컨텍스트에서분리(detached)

* 영속성 컨텍스트가 제공하는 기능을 사용 못함

* 만드는 방법

  * em.detach(entity)
    * 특정 엔티티만 준영속 상태로 전환
  * em.clear()
    * 영속성 컨텍스트를 완전히 초기화
  * em.close()
    * 영속성 컨텍스트를 종료

  