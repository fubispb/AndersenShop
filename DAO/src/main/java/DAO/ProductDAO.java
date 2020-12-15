package DAO;

import entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class ProductDAO extends JdbcDaoSupport {

    EntityManagerFactory entityManagerFactory;

    @Autowired
    public ProductDAO(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
        this.setDataSource(dataSource);
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<ProductEntity> getGeneralProductList() {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> productCriteria = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> productRoot = productCriteria.from(ProductEntity.class);
        productCriteria.select(productRoot);
        return em.createQuery(productCriteria).getResultList();
    }

}
