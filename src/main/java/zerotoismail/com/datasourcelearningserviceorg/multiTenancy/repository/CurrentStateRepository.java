package zerotoismail.com.datasourcelearningserviceorg.multiTenancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerotoismail.com.datasourcelearningserviceorg.multiTenancy.model.CurrentState;

import java.util.Optional;

@Repository
public interface CurrentStateRepository extends JpaRepository<CurrentState,String> {
    public Optional<CurrentState> findByTenantId(String userId);
}
