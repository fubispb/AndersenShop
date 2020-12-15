package DAO;

import entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Slf4j
@Repository
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
public class UserDAO extends JdbcDaoSupport {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserDAO(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
        this.setDataSource(dataSource);
        this.entityManagerFactory = entityManagerFactory;
    }

    public UserEntity getUserById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.find(UserEntity.class, id);
    }

}
