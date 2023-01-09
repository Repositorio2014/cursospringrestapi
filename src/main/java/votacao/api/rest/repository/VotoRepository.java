package votacao.api.rest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import votacao.api.rest.model.Sessao;
import votacao.api.rest.model.Usuario;
import votacao.api.rest.model.Voto;

import java.util.List;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {

    @Query(nativeQuery = true, value = "select v.* from voto v where v.sessao_id = ?1")
    List<Voto> findVotosBySessao(Long id);

}
