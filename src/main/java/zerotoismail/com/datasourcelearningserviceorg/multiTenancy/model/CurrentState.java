package zerotoismail.com.datasourcelearningserviceorg.multiTenancy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CurrentState {
    @Id
    private String tenantId;
    private String data;

    public CurrentState() {}

    public CurrentState(String tenantId, String data) {
        this.tenantId = tenantId;
        this.data = data;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}