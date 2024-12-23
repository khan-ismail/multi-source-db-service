package zerotoismail.com.datasourcelearningserviceorg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import zerotoismail.com.datasourcelearningserviceorg.exception.ResourcesNotFoundException;
import zerotoismail.com.datasourcelearningserviceorg.model.ConnectionConfig;
import zerotoismail.com.datasourcelearningserviceorg.model.MySQLConnectionDetails;
import zerotoismail.com.datasourcelearningserviceorg.repository.ConnectionConfigRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionConfigService {
    private ConnectionConfigRepository repository;

    public ConnectionConfigService(@Lazy ConnectionConfigRepository repository) {
        this.repository = repository;
    }

    public MySQLConnectionDetails getTenantConnection(Long tenantId) {
        ConnectionConfig config = repository.findById(tenantId)
                .orElseThrow(() -> new ResourcesNotFoundException("Tenant Id", "tenantId", tenantId + ""));

        String mysqlJson = config.getMysql();
        if (mysqlJson == null) {
           throw new ResourcesNotFoundException("My Sql Connection Details", "connectionProperties", tenantId + "");
        }

        return MySQLConnectionDetails.fromJson(mysqlJson);
    }

    public List<MySQLConnectionDetails> getAllTenantConnections() {
        List<ConnectionConfig> config = repository.findAll();
        List<MySQLConnectionDetails> connectionDetails = new ArrayList<>();

        for(ConnectionConfig c : config) {
            String mysqlJson = c.getMysql();
            if (mysqlJson == null) {
                throw new ResourcesNotFoundException("My Sql Connection Details", "connectionProperties", "No Configuration");
            }
            connectionDetails.add(MySQLConnectionDetails.fromJson(mysqlJson));
        }
        return connectionDetails;
    }
}
