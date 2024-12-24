package zerotoismail.com.datasourcelearningserviceorg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import zerotoismail.com.datasourcelearningserviceorg.dto.User;
import zerotoismail.com.datasourcelearningserviceorg.multiTenancy.model.CurrentState;
import zerotoismail.com.datasourcelearningserviceorg.multiTenancy.repository.CurrentStateRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Service
public class QueryBuilderService {

    CurrentStateRepository currentStateRepository;
    private final JdbcTemplate jdbcTemplate;

    public QueryBuilderService(@Qualifier("tenantRoutingDataSource") DataSource dataSource,
                               CurrentStateRepository currentStateRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.currentStateRepository = currentStateRepository;
    }

    public List<Map<String, Object>> executeQueryForTenant(String query) {
        return jdbcTemplate.queryForList(query);
    }

    public CurrentState saveCurrentState(CurrentState currentState) {
        return currentStateRepository.save(currentState);
    }

    public Object getConfiguration(String tenantId) {

        if (tenantId == null) {
            return currentStateRepository.findAll();
        } else {
            return currentStateRepository.findById(tenantId);
        }
    }

}