package zerotoismail.com.datasourcelearningserviceorg.config;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class DataSourceInitializer {

    @Autowired
    private DataSource primaryDataSource;

 /*   @Autowired
    private DataSource secondaryDataSource;

    @Autowired
    private DataSource ternaryDataSource;
*/
 private static final Logger logger = LoggerFactory.getLogger(DataSourceInitializer.class);
    @PostConstruct
    public void initializeSchemas() {
       // createSchemaForDataSource(primaryDataSource, "PrimaryDB","interaction.cx.masdr.sa.backend.mapper");
        logger.info("DataSourceInitializer.initializeSchemas........");
     //   createSchemaForDataSource(secondaryDataSource, "SecondaryDB");
     //   createSchemaForDataSource(ternaryDataSource, "TernaryDB");
    }

    public void createSchemaForDataSource(DataSource dataSource, String persistenceUnitName,String packageToScan) {
        Map<String, Object> properties = Map.of(
                Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect",
                Environment.HBM2DDL_AUTO, "update",
                Environment.SHOW_SQL, "true"
        );
        logger.info("DataSourceInitializer.createSchemaForDataSource........");
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan(packageToScan);
        factoryBean.setPersistenceUnitName(persistenceUnitName);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaPropertyMap(properties);
        factoryBean.setPersistenceProvider(new HibernatePersistenceProvider());

        // Initialize the factory bean to create schema
        factoryBean.afterPropertiesSet();
        EntityManagerFactory entityManagerFactory = factoryBean.getObject();

        if (entityManagerFactory != null) {
            entityManagerFactory.close(); // Release resources after schema initialization
        }
    }
}
