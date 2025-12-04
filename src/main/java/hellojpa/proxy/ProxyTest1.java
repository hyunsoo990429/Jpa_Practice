package hellojpa.proxy;

import hellojpa.association.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProxyTest1 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setName("member1");

            em.persist(member);

            em.flush();
            em.clear();

            //실제 엔티티 객체 조회
//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.username = " + findMember.getName());

            //프록시 객체 생성
            Member findProxyMember = em.getReference(Member.class, member.getId());
            System.out.println("findProxyMember.class = " + findProxyMember.getClass());
            System.out.println("findProxyMember.id = " + findProxyMember.getId());
//            System.out.println("findProxyMember.username = " + findProxyMember.getName());


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
