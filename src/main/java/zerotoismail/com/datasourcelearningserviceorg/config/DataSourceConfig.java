package zerotoismail.com.datasourcelearningserviceorg.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zerotoismail.com.datasourcelearningserviceorg.exception.ResourcesNotFoundException;
import zerotoismail.com.datasourcelearningserviceorg.model.MySQLConnectionDetails;
import zerotoismail.com.datasourcelearningserviceorg.service.ConnectionConfigService;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class DataSourceConfig {

    private ConnectionConfigService connectionService;
    private final Map<Long, DataSource> connectionPoolMap;

    public DataSourceConfig(ConnectionConfigService connectionService) {
        this.connectionService = connectionService;
        connectionPoolMap = new ConcurrentHashMap<>();
    }

    @Bean
    public TenantRoutingDataSource tenantDataSource() {
        TenantRoutingDataSource routingDataSource = new TenantRoutingDataSource(connectionService, connectionPoolMap);
        Map<Object, Object> dataSourceMap = new HashMap<>();
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(dataSourceMap);
        routingDataSource.afterPropertiesSet();
        return routingDataSource;
    }

//    @Bean
//    public DataSource dataSource() {
//        TenantRoutingDataSource routingDataSource = new TenantRoutingDataSource(connectionService, connectionPoolMap);
//        Map<Object, Object> dataSourceMap = new ConcurrentHashMap<>();
//        routingDataSource.setTargetDataSources(dataSourceMap);
//        routingDataSource.setDefaultTargetDataSource(dataSourceMap);
//        routingDataSource.afterPropertiesSet();
//        return routingDataSource;
//    }

//    @Bean
//    public DataSource dataSource() {
//        TenantRoutingDataSource routingDataSource = new TenantRoutingDataSource();
//        Map<Object, Object> dataSourceMap = new ConcurrentHashMap<>();
//
//        List<MySQLConnectionDetails> allConnections = connectionService.getAllTenantConnections();
//        for (MySQLConnectionDetails connection : allConnections) {
//            DataSource dataSource = createDataSource(connection);
//            dataSourceMap.put(connection.getTenantId(), dataSource);
//        }
//
//        routingDataSource.setTargetDataSources(dataSourceMap);
//        routingDataSource.afterPropertiesSet();
//        return routingDataSource;
//    }

//    private DataSource createDataSource(MySQLConnectionDetails connection) {
//        HikariConfig config = new HikariConfig();
//        config.setJdbcUrl(connection.getConnectionString());
//        config.setUsername(connection.getUsername());
//        config.setPassword(connection.getPassword());
//        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        config.setMaximumPoolSize(10);
//        config.setMinimumIdle(2);
//        config.setIdleTimeout(300000);
//        config.setMaxLifetime(1800000);
//        config.setConnectionTimeout(30000);
//        return new HikariDataSource(config);
//    }
//
//    public DataSource createNewDynamicDataSource(Long tenantId) {
//        return connectionPoolMap.computeIfAbsent(tenantId, id -> {
//            MySQLConnectionDetails connection = connectionService.getTenantConnection(id);
//            if (connection == null) {
//                throw new ResourcesNotFoundException("Tenant Id", "tenantId", id + "");
//            }
//            HikariConfig config = new HikariConfig();
//            config.setJdbcUrl(connection.getConnectionString());
//            config.setUsername(connection.getUsername());
//            config.setPassword(connection.getPassword());
//            config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//            config.setMaximumPoolSize(10);
//            config.setMinimumIdle(2);
//            config.setIdleTimeout(300000);
//            config.setMaxLifetime(1800000);
//            config.setConnectionTimeout(30000);
//            return new HikariDataSource(config);
//        });
//    }

    @PreDestroy
    public void closeAllDataSources() {
        connectionPoolMap.values().forEach(dataSource -> {
            if (dataSource instanceof HikariDataSource) {
                ((HikariDataSource) dataSource).close();
            }
        });
        connectionPoolMap.clear();
    }
}