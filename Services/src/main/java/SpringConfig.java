import DAO.BucketDAO;
import DAO.ProductDAO;
import DAO.UserDAO;
import service.BucketService;
import service.ConnectBaseService;
import service.ProductService;
import service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@RequiredArgsConstructor
public class SpringConfig implements WebMvcConfigurer {

    private ApplicationContext applicationContext;

    @Bean
    public ConnectBaseService connectBaseService(DataSource dataSource) {
        return new ConnectBaseService(dataSource);
    }

    @Bean
    public ProductService productService(ConnectBaseService connectBaseService, ProductDAO productDAO) {
        return new ProductService(connectBaseService, productDAO);
    }

    @Bean
    public BucketService bucketService(ConnectBaseService connectBaseService, ProductService productService, BucketDAO bucketDAO) {
        return new BucketService(connectBaseService, productService, bucketDAO);
    }

    @Bean
    public UserService userService(ConnectBaseService connectBaseService, BucketService bucketService, UserDAO userDAO) {
        return new UserService(connectBaseService, bucketService, userDAO);
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost/my_shop");
        ds.setUsername("root");
        ds.setPassword("QQferrari9900");
        return ds;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public EntityManagerFactory persistence() {
        return Persistence.createEntityManagerFactory("MyShopCriteria");
    }


}
