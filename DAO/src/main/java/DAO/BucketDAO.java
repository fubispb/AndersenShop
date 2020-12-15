package DAO;

import entity.BucketEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.*;

@Slf4j
@Component
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class BucketDAO {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public BucketDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<BucketEntity> getUserBucketEntityById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<BucketEntity> list = em.createQuery("" +
                "from BucketEntity b " +
                "where b.users_id = :id " +
                "group by b.products_id")
                .setParameter("id", id)
                .getResultList();
        em.close();
        return list;
    }

    public void deleteFromUserBucket(long idUser, long idProduct) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete BucketEntity b where b.users_id = :idUser and b.products_id = :idProduct")
                .setParameter("idUser", idUser)
                .setParameter("idProduct", idProduct)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void insertInUserBucket(long id, int count) {
        EntityManager em = entityManagerFactory.createEntityManager();
        BucketEntity entity = new BucketEntity();
        entity.setUsers_id(1L);
        entity.setProducts_id(id);
        entity.setCount(count);
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

}
