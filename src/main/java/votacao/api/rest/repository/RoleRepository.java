package votacao.api.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import votacao.api.rest.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
