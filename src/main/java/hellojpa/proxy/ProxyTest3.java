package hellojpa.proxy;

import hellojpa.association.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProxyTest3 {

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

            Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1.class = " + m1.getClass());
            System.out.println("m1 = " + m1);

            //영속성 컨텍스트에 찾고자 하는 엔티티가 이미 존재한다면
            //프록시 객체를 생성하도록 호출하여도 실제 엔티티가 반환된다.
            //이게 가능한 이유는 jpa에서는 같은 키 값으로의 검색에 있어서 항상 == 비교를 보장하게끔 하기 때문
            Member r1 = em.getReference(Member.class, member1.getId());
            System.out.println("r1.class = " + r1.getClass());
            System.out.println("r1 = " + r1);

            System.out.println("m1 == r1 : " + (m1 == r1));


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
