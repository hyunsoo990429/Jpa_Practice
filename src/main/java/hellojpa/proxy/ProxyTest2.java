package hellojpa.proxy;

import hellojpa.association.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProxyTest2 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member1 = new Member();
            member1.setName("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setName("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            //실제 엔티티 객체 사용 시 타입이 동일
//            Member m1 = em.find(Member.class, member1.getId());
//            Member m2 = em.find(Member.class, member2.getId());
//            System.out.println("m1 == m1 : " + (m1.getClass() == m2.getClass()));

            //엔티티 객체 사용 vs 프록시 객체 시 타입 비교
            //프록시 객체는 원본 엔티티를 상속받기 떄문에 비교 시에 instanceof를 사용해야 한다.
            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.getReference(Member.class, member2.getId());
            System.out.println("m1 == m1 : " + (m1.getClass() == m2.getClass()));


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
