package zerotoismail.com.datasourcelearningserviceorg.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import zerotoismail.com.datasourcelearningserviceorg.dto.User;

import javax.sql.DataSource;
import java.util.List;

@Service
public class MyService {

    private final JdbcTemplate jdbcTemplate;

    public MyService(@Qualifier("tenantRoutingDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> executeQueryForTenant(Long tenantId) {
        System.out.println("Executing query for tenant " + jdbcTemplate);
        String sql = "SELECT * FROM `User`";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            return user;
        });
    }
}