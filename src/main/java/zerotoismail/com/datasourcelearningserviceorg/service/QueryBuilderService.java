package zerotoismail.com.datasourcelearningserviceorg.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import zerotoismail.com.datasourcelearningserviceorg.dto.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Service
public class QueryBuilderService {

    private final JdbcTemplate jdbcTemplate;

    public QueryBuilderService(@Qualifier("tenantRoutingDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Map<String, Object>> executeQueryForTenant(String query) {
        return jdbcTemplate.queryForList(query);
    }
}