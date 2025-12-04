package hellojpa.basic;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //create
        //createMember(em, tx);

        //read one row
        //readOneMember(em, tx);

        //read multi row
        //readMultiMember(em, tx);

        //update
        //updateMember(em, tx);

        //delete
        //deleteMember(em, tx, emf);

        emf.close();
    }

    private static void deleteMember(EntityManager em, EntityTransaction tx, EntityManagerFactory emf) {
        try {

            MemberTest findMemberTest = em.find(MemberTest.class, 1L);
            em.remove(findMemberTest);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void updateMember(EntityManager em, EntityTransaction tx) {
        try {
            MemberTest findMemberTest = em.find(MemberTest.class, 1L);
            findMemberTest.setName("updateUserName");

            tx.commit();

            System.out.println("id : " + findMemberTest.getId() + " name :" + findMemberTest.getName());
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void readMultiMember(EntityManager em, EntityTransaction tx) {
        try {
            List<MemberTest> findMemberTests = em.createQuery("select m  from Member m", MemberTest.class)
                    .getResultList();

            tx.commit();

            for (MemberTest findMemberTest : findMemberTests) {
                System.out.println("id : " + findMemberTest.getId() + " name :" + findMemberTest.getName());
            }
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void readOneMember(EntityManager em, EntityTransaction tx) {
        try {
            MemberTest findMemberTest = em.find(MemberTest.class, 1L);
            tx.commit();

            System.out.println("id : " + findMemberTest.getId() + " name :" + findMemberTest.getName());
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void createMember(EntityManager em, EntityTransaction tx) {
        try {
            MemberTest memberTest1 = new MemberTest();
            memberTest1.setId(1L);
            memberTest1.setName("Test1");
            em.persist(memberTest1);

            MemberTest memberTest2 = new MemberTest();
            memberTest2.setId(2L);
            memberTest2.setName("Test2");
            em.persist(memberTest2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
