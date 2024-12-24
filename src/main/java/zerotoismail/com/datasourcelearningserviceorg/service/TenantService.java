package zerotoismail.com.datasourcelearningserviceorg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zerotoismail.com.datasourcelearningserviceorg.model.ConnectionConfig;
import zerotoismail.com.datasourcelearningserviceorg.repository.ConnectionConfigRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TenantService {

    private ConnectionConfigRepository connectionConfigRepository;

    public TenantService(ConnectionConfigRepository connectionConfigRepository) {
        this.connectionConfigRepository = connectionConfigRepository;
    }

    public List<String> getAllTenantIds() {
        List<String> tenantIds = new ArrayList<>();
        try{
            tenantIds = connectionConfigRepository
                    .findAll()
                    .stream()
                    .map(domain -> domain.getApiDomain()
                            .replace("api.", "")
                            .replace(".com", ""))
                    .toList();

            return tenantIds;
        } catch (Exception e){
            return tenantIds;
        }
    }
}
