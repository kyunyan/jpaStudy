package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // 파라메터로는 persistence unit name 을 넘겨야 한다.
        // EntityManagerFactory 어플리 케이션 로딩시점에 딱 하나만 만들어야 된다.
        EntityManager em = emf.createEntityManager();   // 고객이 들어와서 행위를 하고 나갈때 디비 커넥션을 날리고 커넥션을 종료시키는 작업 할때마다 만들어 주어야 한다.

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // Jpa 에서는 트랜잭션이라는 모든 변경하는 데이터는 트렌젝션 안에서 해줘야 한다.

        try {/*
            // 신규
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");

            em.persist(member);*/

            // 조회
            // 엔티티 매니저를 자바 켈렉션으로 사용한다.
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id : "+findMember.getId());
            System.out.println("findMember.Name : "+findMember.getName());

            // 삭제
            em.remove(findMember);

            // 수정
            // 자바 컬렉션 처럼 사용하기때문에 persist 메소드를 사용하지 않더라도 update 쿼리가 나간다.
            findMember.setName("HelloJPA");
            tx.commit();        // 커밋
        }catch (Exception e){
            tx.rollback();      // 애러가 나면 rollback;
        }finally {
            em.close();
        }

        emf.close();
    }
}

/*
* EntityManagerFactory 를 통해서 고객의 요청이 올때마다 EntityManager 생성한다.
* 역속성 컨텍스트란 엔티티를 영구 저장하는 환경
* 영속성 컨텍스트는 논리적인 개념 / 눈에 보이지 않는다 / 엔티티 매니저를 통해서 영속성 컨텍스트에 접근한다.
* 엔티티 매니저와 영속성 컨텍스트가 1:1 EntitiManger -> PersistenceContext 엔티티 매니저 안에 영속성 컨텍스트 눈에 보이지 않게 안에 생성된다.
* 엔티티의 생명주기
* - 비영속 : 영속성 컨텍스트와 전혀 관계가 없는 새로운 상태
* - 영속 : 영속성 컨텍스트에 관리 되는 상태 / persist 하면 된다.
* - 준영속 : 영속성 컨텍스트에 저장되었다가 분리된 상태
* - 삭제 : 삭제된 상태
* */
/*
public class JpaMain {

    public static void main(String[] args){
        // persistance 등록된 name 을 등록한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 웹서버에서 올라갈때 하나만 생성된다 디비당 하나

        EntityManager em = emf.createEntityManager();      // create entity manger 를 꺼내야된다.
        // 고객의 요청이 올때마다 생성됫다가 없어졋다가 한다. 스레드 간의 공유 절대 x(사용하고 버려야된다.)
        // jqp 모든 데이터 변경은 트렌젝션 안에서 해야된다.
        // 로딩 시킬때 단 하나만 생성시켜야된다.

        EntityTransaction tx = em.getTransaction();    // 트랜잭션 단위에서 실행시켜야된다
        tx.begin();
        try {*/
            /*
            // insert
            // 비영속
            Member member = new Member();
            member.setId(4L);
            member.setName("HelloB");

            // 영속
            em.persist(member); // member 를 저장한다
            // select
            Member findMember = em.find(Member.class , 1L);
            System.out.println("findMember.id = "+findMember.getId());
            System.out.println("findMeber.name = "+findMember.getName());
            // delete
            Member findMember = em.find(Member.class , 4L);
            em.remove(findMember);
            // update
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("이순신");
            */

            // jpql
            // table 기준이 아닌 객체를 대상으로 쿼리를 날린다.
            // m 은 Member entity 를 기준으로 날린다.
            /*
            *  jpa 를 사용하면 엔티티 객체를 중심으로 개발
            *   문제는 검색 쿼리
            *  검색을 할때도 테이블이 아닌 엔티티 객체를 대상으로 검색
            *  모든 DB데이터를 객체로 변환해서 검색 하는 것은 불가능
            *  어플리케이션이 필요한 e데이터만 DB에 불러오려면 검색 조건이 포함된 SQL 이 필요
            *  테이블이 아닌 객체를 대상으로 검색하는 객체 지향 쿼리
            *  SQL을 추상화 해서 특정 데이터 베이스 SQL의존 x
            *  jpql을 한마디로 정의하면 객체지향 SQL
            *
            * */
            /*
            List<Member1> result =  em.createQuery("select m from Member1 as m", Member1.class)
                    .setFirstResult(1)
                    .setMaxResults(2)
                    .getResultList();

            for(Member member :result){
                System.out.println("Member.name = "+member.getName());
            }
            */
/*
            Member2 member2 = new Member2();

            // member.setId("ID_A");
            member2.setName("test1");

            System.out.println("===============");
            em.persist(member2);     // 원래는 commit 할때 쿼리가 날라가는데 이 키가 jenerate value 일때만 여기서 insert 쿼리를 날린다.
            System.out.println(member2.getId());
            System.out.println("===============");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();


    }
}
*/
/*
public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member3 member = new Member3();
            member.setUsername("memeber3");
            member.changeTeam(team);
            em.persist(member);

            //em.flush();
            //em.clear();

            Member3 findMember = em.find(Member3.class, member.getId());

            List<Member3> members = findMember.getTeam().getMembers();

            for(Member3 m : members){
                System.out.println("m = "+m.getUsername());
            }

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}

*/