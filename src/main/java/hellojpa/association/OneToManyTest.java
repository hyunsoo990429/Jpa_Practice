package hellojpa.association;

import hellojpa.association.entity.Member;
import hellojpa.association.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class OneToManyTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // Member와 Team은 1 : N 관계이다, 앞선 AssociationTest에서는 N의 관계를 지닌 측에 외래키가 존재하기 때문에
            // 그에 맞춰 Member 엔티티에 @JoinColumn을 두었다.
            // 권장하지는 않지만 1의 관계를 지닌 측에도 @JoinColumn을 사용할 수 있긴 하다, 해당 테스트는 그 예시를 진행해본다.
            // 이 경우 Member 엔티티에는 Team 엔티티에 관련된 것이 아무것도 없고
            // Team 엔티티 내부에 있는 List<Member> members 컬럼에 @JoinColumn(name = "Team_ID")로 외래키 매핑이 되어 있다.(필요 시 엔티티 변경 후 테스트)

            Member member = new Member();
            member.setName("member1");
            em.persist(member);

            Team team = new Team();
            team.setName("team1");

            //추가적인 쿼리문의 발생 + 내가 조작하지 않은 엔티티에 영향을 주느 것이기 때문에 코드 해석이 쉽지 않다.
            team.getMembers().add(member);
            em.persist(team);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
