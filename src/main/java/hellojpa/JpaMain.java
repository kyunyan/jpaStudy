package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
        try {
            /*
            // insert
            Member member = new Member();
            member.setId(4L);
            member.setName("HelloB");
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
            List<Member> result =  em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(0)
                    .setMaxResults(1)
                    .getResultList();

            for(Member member :result){
                System.out.println("Member.name = "+member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();


    }
}
