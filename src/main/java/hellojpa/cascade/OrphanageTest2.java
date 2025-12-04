package hellojpa.cascade;

import hellojpa.cascade.entity.Child;
import hellojpa.cascade.entity.Parent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class OrphanageTest2 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //Cascade 옵션 없이 orphanRemoval 옵션을 true로 두면 엔티티에서 더 이상 참조하지 않는 연관된 엔티티를 db에서 실제로 제거한다.
            //Cascade 옵션의 REMOVE, ALL 경우에는 참조하지 않는 연관된 엔티티를 실제로 제거할 수는 없고 엔티티의 삭제 시 연관된 엔티티의 삭제가 같이 이루어진다.
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
            em.persist(child1);
            em.persist(child2);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildren().remove(0);
            //em.remove(findParent);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
