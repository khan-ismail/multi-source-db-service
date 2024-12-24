package zerotoismail.com.datasourcelearningserviceorg.config;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import zerotoismail.com.datasourcelearningserviceorg.exception.ResourcesNotFoundException;
import zerotoismail.com.datasourcelearningserviceorg.model.MySQLConnectionDetails;
import zerotoismail.com.datasourcelearningserviceorg.service.ConnectionConfigService;

import javax.sql.DataSource;
import java.util.Map;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    private ConnectionConfigService connectionService;
    private Map<Long, DataSource> dataSourceMap;

    public TenantRoutingDataSource(ConnectionConfigService connectionService, Map<Long, DataSource> dataSourceMap) {
        this.connectionService = connectionService;
        this.dataSourceMap = dataSourceMap;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getCurrentTenant();
    }

    @Override
    protected DataSource determineTargetDataSource() {
        Long tenantId = (Long) determineCurrentLookupKey();
        return dataSourceMap.computeIfAbsent(tenantId, id -> {
            MySQLConnectionDetails connection = connectionService.getTenantConnection(id);
            if (connection == null) {
                throw new ResourcesNotFoundException("Tenant Id", "tenantId", id + "");
            }
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(connection.getConnectionString());
            config.setUsername(connection.getUsername());
            config.setPassword(connection.getPassword());
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setIdleTimeout(300000);
            config.setMaxLifetime(1800000);
            config.setConnectionTimeout(30000);
            return new HikariDataSource(config);
        });
    }
}
