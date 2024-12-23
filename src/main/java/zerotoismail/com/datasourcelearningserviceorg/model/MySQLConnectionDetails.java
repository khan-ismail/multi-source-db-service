package zerotoismail.com.datasourcelearningserviceorg.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MySQLConnectionDetails {
    private String username;
    private String password;
    private String connectionString;

    public static MySQLConnectionDetails fromJson(String connectionDetailsJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(connectionDetailsJson, MySQLConnectionDetails.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse MySQL connection details", e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
}