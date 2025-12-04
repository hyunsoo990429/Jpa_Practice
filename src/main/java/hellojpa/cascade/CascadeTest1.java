package hellojpa.cascade;

import hellojpa.association.entity.Member;
import hellojpa.association.entity.Team;
import hellojpa.cascade.entity.Child;
import hellojpa.cascade.entity.Parent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CascadeTest1 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            //Cascade 옵션 시 연관된 객체 또한 영속성이 전이 되게 되어 persist가 필요없다.
            //em.persist(child1);
            //em.persist(child2);



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
