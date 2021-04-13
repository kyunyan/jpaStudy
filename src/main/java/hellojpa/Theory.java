package hellojpa;

public class Theory {
    /*
    * 영속성 관리
    * jpa 에서 가장 중요한 2가지
    * - 객체와 관계형 데이터 베이스 매핑하기
    * - 영속성 컨텍스트
    *   - 엔티티를 영구 저장하는 환경이라는 뜻
    *   - persist 메소드는 디비에 저장 하는 것이 아니라 엔테티를 영속성 컨텍스에 저장한다는 개념이다
    *   - 영속성 컨텍스트는 논리적인 개념이다
    *   - 눈에 보이지 않는다
    *   - 엔티티 매니저를 통해서 영속성 컨텍스트에 접근한다.
    *
    * - 엔티티 생명주기 new
    *   - 비영속 : 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
    *   - 영속 : 영속성 컨텍스트에 관리되는 상태 ex) persist 를 하면 영속성컨텍스트에 관리되게 된다.
    *   - 준영속 : 영속성 컨텍스트에 저장되었다가 분리 된 상태
    *   - 삭제 : 삭제된 상태
    *  비영속
    * Member member = new Member();
    * member.setId("member1");
    * member.setUsername("회원1");
    *
    * 영속
    * Member member = new Member();
    * member.setId("member1");
    * member.setUsername("회원1");
    *
    * EntityManger em = emf.createEntityManger();
    * em.getTransaction().begin();
    *
    * // 객체를 저장한 상태(영속)
    * em.persist(member)
    * tx.commit(); // 영속성 컨텍스트에 있다고 쿼리가 날라가는것이 아니라 커밋을 할때 쿼리가 실행된다.
    *
    * // 준영속 영속성 컨텍스트 를 지운다
    * em.detach(member)
    *
    * // 디비에서 데이터를 지우겟다
    * em.remove(member)
    *
    * 영속성 컨텍스트의 이점
    * -1차캐시
    * -동일성보장
    * -트랜잭션을 지원하는 쓰기 지연
    * -변경감지
    * -지연로딩
    *
    * EntityManager Facotry
    * -
    * EntitiManager
    * - 엔티티 메니저 팩토리를 생성한다 고객의 요청이 올때마다
    * - 데이터 베이스 커넥션을 통해서 디비를 사용하게 된다.
    *
    * 엔티티 조회 1차 캐시
    * 영속성 컨텍스트(entityManager)
    * 이안에 1차 캐시가 있다
    *
    * Member member = new Member();
    * member.setId("member1");
    * member.setUserName("회원1")
    *
    * em.persist(member) // 1차 캐시에 넣는다
    *
    * // 1차 캐시에서 조회
    * Member findMember = em.find(Member.class, "member1");
    *
    * // 1차 캐시에 없으면 디비에서 조회
    * Member findMember2 = em.find(Member.class, "member2");
    *
    * 엔티티 매니저는 데이터 베이스 태랜잭션 단위로 만들고 트랜잭션 끝나면 없애버린다.
    *
    * 영속 엔티티의 동일성 보장
    * Member a = em.find(Member.class , "member1");
    * Member b = em.find(Member.class, "member1");
    *
    * System.out.println(a == b); //동일성 비교 true
    *
    * 자바 컬랙션과 똑같이 == 비교를 보장해준다.
    * 1차 캐시로 반복가능한 읽기 등급의 트랜잭션 격리 수준을 데이테베이슥 아닌 어플리케이션 차원에서 제공한다.
    *
    * 엔티티 등록
    * 트랜잭션을 지원하는 쓰기 지연
    * // 엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다
    * transaction.begin()   // 트랜잭션 시작
    *
    * em.persist(memberA);
    * em.persist(memberB);
    * // 여기까지 INSERT SQL을 데이터 베이스에 보내지 않는다.
    * persist 를 햇을때 1차캐시에 들어가면서 엔티티를 분석해서 쓰기지연 SQL 저장소에 들어간다.
    * batch size 설정을 통해 모았다가 한번에 데이터를 보낼수 있다.
    *
    * // 커밋하는 순간 데이터 베이스에 INSERT SQL을 보낸다
    * transaction.commit();
    *
    * 엔티티 수정 변경감지가 가능하다.
    * 변경 감지 setter를 통해 데이터를 저장 시킬수 있다.
    * 컬렉션과 같이 저장되기때문에 persist 를 쓰지 않더라도 저장 가능하다.
    * 커밋을 하게 되면 flush 가 호출되는데 entity랑 스냅샷을 비교한다
    * 1차 캐시 안에는 entity랑 스냅샷이 존재한다.
    * entity와 스냅샷을 비교해서 변경된 내역이 있다면 update 를 날린다
    *
    * 엔티티 삭제
    * em.remove(memberA);
    *
    * 플러쉬
    * 영속성 컨텍스트의 변경내용을 데이터베이스에 반영
    *
    * 플러쉬 발생
    * 변경 감지
    * 수정된 엔티티에 쓰기 지연  SQL 저장소에 등록
    * 쓰기지연 sQL 저장소의 쿼리를 데이터베이스에 젆송
    *
    * em.flush() -- 직접호출
    * 트랜잭션 커밋 - 플러시 자동 호출
    * JPQL 쿼리 실행 - 플러시 자동 호출
    *
    * Member member = new Member(100L , "member23");
    * em.persist(member);
    *
    * em.flush();   // 쿼리가 즉시 실행된다.
    * // flush 를한다고 1차캐시 를 날리는것이 아니라 쓰기 지연 SQL 저장소에 있는 것만 데이터 베이스에 반영이되는 과정이다.
    *
    * jqpl 쿼리 실행시 플러시가 자동 실행된다.
    *
    * 플러시는
    * - 영속성 컨텍스를 비우는게 아니다
    * - 영속성 컨텍스트의 변경 내용을 데이터베이스에 동기화하낟.
    * - 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화 하면 됨.
    *
    * 준영속 상태
    * 영속 -> 준영속
    * em.persist()  1차 캐시에 있는 상태는  영속상태이다.
    * em.find() 영속성 컨텍스트에 없으면 찾기때문에 영속상태가된다.
    *
    * - 영속 상태의 엔티티 영속성 컨텍스트에서 분리
    * - 영속성 컨텍스트가 제공하는 기능을 사용하지 못함
    *
    * 준영속 상태로 만드는 방법
    * em.detach(entity) - 특정 엔티티만 중영속 상태로 전환
    * em.clear();  - 영속성 컨텍스트를 완전히 초기화
    * em.close(); - 영속성 컨텍스트를 종료
    *
    * Member member = em.find(Member.class , 150L)
    * member.setNAme("AAAAA");
    *
    * em.detach(member);        // 이렇게 하면 update 쿼리 실행하지 않는다.
    *
    *
    * 엔티티 매핑소개
    * 객체와 테이블 매핑 : @Entity, @Table
    * 필드와 컬럼 매핑 : @Column
    * 기본 키 매핑 : @Id
    * 연관관계 매핑 : @ManyToOne, @JoinColumn
    *
    * @Entity
    * @Entity 가 붙은 클래스는 JPA가 관리, 인테티라 한다.
    * JPA를 사용해서 테이블과 매핑할 클래스는 @Entity 필수
    *
    * 주의
    * 기본생성자 필수 (파라미터가 없는 public 또는 protected 생성자)
    * final 클래스 , enum, interface in
    *
    * @Entity 정리
    * 속성 : name
    *
    * JPA에서 사용할 엔티티 이름을 지정한다.
    * 기본값 : 클래스 이름을 그대로 사용(예 : Member)
    * 같은 클래스 이름이 없으면 가급적 기본값 사용
    *
    * @Table
    * name : 매핑할 테이블 이름
    * catalog : 데이터베이스 catalog 매핑
    * schema : 데이터베이스 schema 매핑
    *
    * hibernate.hbm2ddl.auto
    * create - 기존테이블 삭제후 다시 생성
    * create-drop - 기존 테이블 삭제후 다시생성후 다시 삭제
    * update - 변경 부분만
    * validate - 엔티티와 테이블이 정상 매핑되었는지 확인해준다.
    * none - 사용되지 않음
    *
    * 운영장비에는 절대 create , create-drop , update 사용하면 안된다.
    * 개발 초기 단계에는 create 또는 update
    *
    * 테스트 서버는 update 또는 validate
    *
    * 스테이징과 운영서버는 validate 또는 none
    *
    *
    *
    *
    * */
}
