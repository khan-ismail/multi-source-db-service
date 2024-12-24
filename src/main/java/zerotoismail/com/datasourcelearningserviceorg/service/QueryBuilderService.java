package zerotoismail.com.datasourcelearningserviceorg.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import zerotoismail.com.datasourcelearningserviceorg.dto.User;

import javax.sql.DataSource;
import java.util.List;

@Service
public class QueryBuilderService {

    private final JdbcTemplate jdbcTemplate;

    public QueryBuilderService(@Qualifier("tenantRoutingDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> executeQueryForTenant(String query) {
        System.out.println("Executing query for tenant " + jdbcTemplate);
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            return user;
        });
    }
}