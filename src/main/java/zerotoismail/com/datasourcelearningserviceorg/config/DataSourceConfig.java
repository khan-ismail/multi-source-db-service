/*
package interaction.cx.masdr.sa.backend.config;

import com.zaxxer.hikari.HikariDataSource;
import interaction.cx.masdr.sa.backend.config.TenantRoutingDataSource;
import interaction.cx.masdr.sa.backend.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.primary.url}")
    private String primaryDbUrl;

    @Value("${spring.datasource.primary.username}")
    private String primaryDbUsername;

    @Value("${spring.datasource.primary.password}")
    private String primaryDbPassword;
*/
/*

    @Value("${spring.datasource.secondary.url}")
    private String secondaryDbUrl;

    @Value("${spring.datasource.secondary.username}")
    private String secondaryDbUsername;

    @Value("${spring.datasource.secondary.password}")
    private String secondaryDbPassword;
    @Value("${spring.datasource.ternary.url}")
    private String ternaryDbUrl;

    @Value("${spring.datasource.ternary.username}")
    private String ternaryDbUsername;

    @Value("${spring.datasource.ternary.password}")
    private String ternaryDbPassword;
*//*

public final Map<Object, Object> dataSources = new ConcurrentHashMap<>();
private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    @Bean
    public DataSource primaryDataSource() {
        logger.info("DataSourceConfig.primaryDataSource ........");
        return createDataSource(primaryDbUrl, primaryDbUsername, primaryDbPassword);
    }

  */
/*  @Bean
    public DataSource secondaryDataSource() {
        return createDataSource(secondaryDbUrl, secondaryDbUsername, secondaryDbPassword);
    }
    @Bean
    public DataSource ternaryDataSource() {
        return createDataSource(ternaryDbUrl, ternaryDbUsername, ternaryDbPassword);
    }*//*


    @Bean
    public TenantRoutingDataSource tenantRoutingDataSource() {
        TenantRoutingDataSource dataSource = new TenantRoutingDataSource();
       // Map<Object, Object> targetDataSources = new HashMap<>();
        logger.info("DataSourceConfig.tenantRoutingDataSource ........");
        dataSources.put("primary", primaryDataSource()); // Mapping tenant1 to the primary data source
     //   targetDataSources.put("tenant2", secondaryDataSource()); // Mapping tenant2 to the secondary data source
       // targetDataSources.put("tenant3", ternaryDataSource());
        dataSource.setTargetDataSources(dataSources);
        dataSource.setDefaultTargetDataSource(primaryDataSource()); // Default to primary if tenant is not found
        return dataSource;
    }

    public DataSource createDataSource(String url, String username, String password) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        logger.info("DataSourceConfig.createDataSource.......");
        return dataSource;
    }

}
*/
package zerotoismail.com.datasourcelearningserviceorg.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import zerotoismail.com.datasourcelearningserviceorg.service.ConnectionConfigService;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String primaryDbUrl;

    @Value("${spring.datasource.username}")
    private String primaryDbUsername;

    @Value("${spring.datasource.password}")
    private String primaryDbPassword;

    public final Map<Object, Object> dataSources = new ConcurrentHashMap<>();

    private ConnectionConfigService connectionService;
    private final Map<Long, DataSource> connectionPoolMap;

    public DataSourceConfig(ConnectionConfigService connectionService) {
        this.connectionService = connectionService;
        connectionPoolMap = new ConcurrentHashMap<>();
    }

    @Bean
    @Primary
    public DataSource primaryDataSource() {
        return createDataSource(primaryDbUrl, primaryDbUsername, primaryDbPassword);
    }

    @Bean(name="tenantRoutingDataSource")
    public TenantRoutingDataSource tenantRoutingDataSource() {
        TenantRoutingDataSource dataSource = new TenantRoutingDataSource(connectionService, connectionPoolMap);
        DataSource primary = primaryDataSource();
        dataSources.put("primary", primary);
        dataSource.setTargetDataSources(dataSources);
//        dataSource.setDefaultTargetDataSource(primary);
        return dataSource;
    }

    public DataSource createDataSource(String url, String username, String password) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }
}
