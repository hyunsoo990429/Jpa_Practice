package hellojpa.basic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EntityLifeCycle {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //비영속
            MemberTest memberTest = new MemberTest();
            memberTest.setId(101L);
            memberTest.setName("HelloJPA");

            //영속
            System.out.println("==== BEFORE ====");
            em.persist(memberTest);
            System.out.println("==== AFTER ====");

            MemberTest findMemberTest = em.find(MemberTest.class, 101L);
            System.out.println("findMember.id: " + findMemberTest.getId() + " findMember.name: " + findMemberTest.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
