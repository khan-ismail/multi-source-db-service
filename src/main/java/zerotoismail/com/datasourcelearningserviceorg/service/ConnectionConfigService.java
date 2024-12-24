package zerotoismail.com.datasourcelearningserviceorg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import zerotoismail.com.datasourcelearningserviceorg.exception.ResourcesNotFoundException;
import zerotoismail.com.datasourcelearningserviceorg.model.ConnectionConfig;
import zerotoismail.com.datasourcelearningserviceorg.model.MySQLConnectionDetails;
import zerotoismail.com.datasourcelearningserviceorg.repository.ConnectionConfigRepository;


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
}
