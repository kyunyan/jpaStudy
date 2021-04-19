package jpabook.jpashop;

import hellojpa.Member2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {


    public static void main(String[] args){
        // persistance 등록된 name 을 등록한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();      // create entity manger 를 꺼내야된다.

        EntityTransaction tx = em.getTransaction();    // 트랜잭션 단위에서 실행시켜야된다
        tx.begin();
        try {

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();


    }
}
