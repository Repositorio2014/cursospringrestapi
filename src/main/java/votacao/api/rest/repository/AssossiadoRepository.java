package votacao.api.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import votacao.api.rest.model.Assossiado;

@Repository
public interface AssossiadoRepository extends CrudRepository<Assossiado, Long> {
}
