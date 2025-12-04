package hellojpa.valuetype.embedded;

import hellojpa.valuetype.entity.Address;
import hellojpa.valuetype.entity.Period;
import hellojpa.valuetype.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class EmbeddedTest1 {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            User user = new User();
            user.setUserName("testUser");
            user.setHomeAddress(new Address("city", "street", "zipcode"));

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime end = now.plusHours(6);
            user.setWorkPeriod(new Period(now, end));

            em.persist(user);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
