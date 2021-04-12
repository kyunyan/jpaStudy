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
    *
    *
    *
    *
    *
    * */
}
