package hellojpa.load;

import hellojpa.association.entity.Member;
import hellojpa.association.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class LazyLoadTest1 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();


            Member findMember = em.find(Member.class, member.getId());

            //지연 로딩 Lazy로 인해 프록시 객체 사용
            //이 시점에는 아직 team관련 정보에 대한 요청이 없기 때문에 프록시 객체가 초기화 되지 않았다.
            System.out.println("findMember.getTeam().class = " + findMember.getTeam().getClass());

            //이 시점에 team 프록시 객체 초기화
            System.out.println("findMember.getTeam().name = " + findMember.getTeam().getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
