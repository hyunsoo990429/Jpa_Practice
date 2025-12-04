package hellojpa.jpql;

import hellojpa.association.entity.Member;
import hellojpa.cascade.entity.Child;
import hellojpa.cascade.entity.Parent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class Basic1 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            List<Member> resultList = em.createQuery("select m from Member m where m.name like '%test%'", Member.class).getResultList();

            for (Member member : resultList) {
                System.out.println("member = " + member);
            }

            em.createNativeQuery("select MEMBER_ID, USERNAME from MEMBER").getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
