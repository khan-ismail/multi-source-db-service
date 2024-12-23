package zerotoismail.com.datasourcelearningserviceorg.service;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import zerotoismail.com.datasourcelearningserviceorg.config.DataSourceConfig;
import zerotoismail.com.datasourcelearningserviceorg.dto.User;

import javax.sql.DataSource;
import java.util.List;

@Service
public class MyService {

//    private final DataSourceConfig dataSourceConfig;
//
//    public MyService(DataSourceConfig dataSourceConfig) {
//        this.dataSourceConfig = dataSourceConfig;
//    }
//
//    public List<User> executeQueryForTenant(Long tenantId) {
//        DataSource dataSource = dataSourceConfig.createNewDynamicDataSource(tenantId);
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        String sql = "SELECT * FROM User";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            User user = new User();
//            user.setId(rs.getLong("id"));
//            user.setName(rs.getString("name"));
//            return user;
//        });
//    }

    private final JdbcTemplate jdbcTemplate;

    public MyService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<User> executeQueryForTenant(Long tenantId) {
        String sql = "SELECT * FROM User";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            return user;
        });
    }
}