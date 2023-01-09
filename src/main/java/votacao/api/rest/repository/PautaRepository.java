package votacao.api.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import votacao.api.rest.model.Pauta;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Long> {
}
