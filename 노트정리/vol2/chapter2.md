##2장
- DAO 패턴
  - DAO는 인터페이스를 이용해 접근하고 DI 되도록 만들어ㅏ야한다.
  - 인터페이스에서는 구체적인 데이터 액세스 기술과 관련된 어떤 API 나 정보도 노출하지 않는다.
  - 습관적으로 public 메소드를 사용하는 것이아닌 서비스 계층에서 유의미한 메소드만 public 으로 설정되어야 한다.
  - JPA의 ```persist()```, ```merge()``` 보다는 일반/보편적인 ```add()```, ```update()``` 와 같은 이름을 선택하자.
- 예외처리
  - 최근 데이터 액세스 기술은 JDBC 와는 다르게 런타임 예외를 사용하므로 서비스 계층에 유의미한 예외를 던질 필요가 없다면 스프링의 AOP와 같은 기술로 예외 변환 서비스를 사용하지 않는다.
   
## DataSource

  - 보통은 요청마다 애플리케이션 서버와 DB 사이에 connection 을 새롭게 만드는 것이아닌 미리 정해진 갯수만큼의 DB 커넥션을 풀에 준비해두고,
      - 애플리케이션이 요청할 때마다 풀에서 꺼내 하나씩 할당해주고 다시 돌려받아서 풀에 넣는 식의 풀링 기법을 이용한다.
  - 스프링에서는 Datasource를 하나의 빈으로 등록하도록 강력하게 권장한다.
   
### 스프링에서 사용되는 주요 DataSource의 종류

- 학습 테스트와 통합 테스트를 위한 DataSource
  - SimpleDriverDataSource
    - 스프링이 제공하는 가장 단순한 DataSource 구현클래스
    - 매번 커넥션으 ㄹ새로 만들고 따로 풀을 관리하지 않는다.
    - 따라서 실전 사용 불가.
  - SingleConnectionDataSource
    - 하나의 물리적인 DB 커넥션만 만들어두고 이를 계속 사용한다.
    - 순차적으로 진행되는 통합 테스트에서는 사용가능하지만 동시에 두개 이상의 스레드가 동작하는 경우에는 하나의 커넥션을 공유하게 되므로 위험하다.
- 오픈소스 또는 상용 DB 커넥션 풀
  - 아파치 Commons DBCP
  - c3p0 JDBC/ DataSource Resource Pool
  - 상용 DB 커넥션풀
- JNDI / WAS DB 풀
 
###JDBC

- 자바의 데이터 액세스 기술의 기본이 되는 로우레벨의 API
- 스프링 JDBC기술과 동작 원리
  - 스프링의 JDBC 접근방법
    - SimpleJDbcTemplate
    - SimpleJdbcInsert, SImpleJdbcCall
  - 스프링 JDBC가 해주는 작업
    - COnnection 열기와 닫기
    - statement준비와 닫기
    - statement 실행
    - ResultSet 루프
    - 예외처리와 변환
    - 트랜잭션 처리

###SimpleJdbcTemplate

- JPA 퍼시스턴스 컨텍스트에 접근하고 엔티티 인스턴스를 관리하려면 JPA의 핵심 인터페이스인 EntityManager를 구현한 오브젝트가 필요하다.
- EntityManager는 JPA에서 두가지 방식으로 관리된다.
  - 하나는 애플리케이션이 관리하는 EntityManager이고,
  - 다른 하나는 컨테이너가 관리하는 EntityManager 다.

