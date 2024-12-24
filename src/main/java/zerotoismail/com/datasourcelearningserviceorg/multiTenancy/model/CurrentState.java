package zerotoismail.com.datasourcelearningserviceorg.multiTenancy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "current_state")
public class CurrentState {
    @Id
    @Column(name="tenant_id")
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