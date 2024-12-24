package zerotoismail.com.datasourcelearningserviceorg.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "zerotoismail.com.datasourcelearningserviceorg.multiTenancy.repository",
        entityManagerFactoryRef = "tenantEntityManagerFactory",
        transactionManagerRef = "tenantTransactionManager"
)
public class TenantDataSourceConfig {

    @Bean(name = "tenantEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("tenantRoutingDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("zerotoismail.com.datasourcelearningserviceorg.multiTenancy.model")
                .persistenceUnit("tenantPU")
                .build();
    }

    @Bean
    public PlatformTransactionManager tenantTransactionManager(
            @Qualifier("tenantEntityManagerFactory") LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory) {
        return new JpaTransactionManager(tenantEntityManagerFactory.getObject());
    }
    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("tenantRoutingDataSource") DataSource dataSource) {
        System.out.println("Initializing JDBC Template");
        return new JdbcTemplate(dataSource);
    }
}
