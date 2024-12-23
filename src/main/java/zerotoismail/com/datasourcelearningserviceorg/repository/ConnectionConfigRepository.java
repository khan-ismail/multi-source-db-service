package zerotoismail.com.datasourcelearningserviceorg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerotoismail.com.datasourcelearningserviceorg.model.ConnectionConfig;

@Repository
public interface ConnectionConfigRepository extends JpaRepository<ConnectionConfig, Long> {
}
