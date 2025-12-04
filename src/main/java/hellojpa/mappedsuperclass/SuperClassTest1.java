package hellojpa.mappedsuperclass;

import hellojpa.inheritance.entity.Movie;
import hellojpa.mappedsuperclass.entity.Seller;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class SuperClassTest1 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Seller seller = new Seller();
            seller.setShopName("testShop");
            seller.setCreatedBy("testSeller");
            seller.setCreatedDate(LocalDateTime.now());

            em.persist(seller);



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
