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

  * 

* 변경 감지(Dirty Checking)

* 지연 로딩(Lazy Loading)

