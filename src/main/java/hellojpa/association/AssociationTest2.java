package hellojpa.association;

import hellojpa.association.entity.Member;
import hellojpa.association.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class AssociationTest2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            /*
            //양방향 관계에서의 실수-연관 관계 주인 쪽에 값을 입력하지 않는 경우
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");
            team.getMembers().add(member);

            em.persist(member);
             */

            //양방향 관계에서는 주인 쪽에 값을 항상 입력해야 한다.
            //추가적으로 순수한 객체 관계를 고려해 slave쪽에도 값을 넣어준다.
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");

            /*
            //이 경우 연관 관계 편의 메서드를 통해 setter를 대체하고 중요도를 높이는 것이 좋다.
            team.getMembers().add(member);
            member.setTeam(team);
             */
            member.addTeam(team);
            em.persist(member);

//            em.flush();
//            em.clear();

            List<Member> members = team.getMembers();
            for (Member m : members) {
                System.out.println("member = " + m.getName());
            }





            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
