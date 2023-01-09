package votacao.api.rest.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import votacao.api.rest.model.Sessao;
import votacao.api.rest.model.Usuario;
import votacao.api.rest.model.Voto;

@Repository
public interface SessaoRepository extends CrudRepository<Sessao, Long> {

    /*@Query(nativeQuery = true, value = "select distinct p.nome as pauta, "
            + "(select count(distinct vt.id) from voto vt "
            + "where vt.sim_nao = 0 "
            + "and vt.sessao_id = s.id) as sim, "
            + "(select count(distinct vtd.id) from voto vtd "
            + "where vtd.sim_nao = 1 "
            + "and vtd.sessao_id = s.id) as nao "
            + "from sessao s "
            + "inner join voto v on v.sessao_id = s.id "
            + "inner join assossiado a on a.id = v.assossiado_id "
            + "inner join pauta p on p.id = s.pauta_id "
            + "where s.id = ?"
    )
    Sessao resultadoVotacao(Long sessaoId);*/

    @Query("select s from Sessao s where s.id = ?1")
    Sessao findSessaoById(Long id);

    @Query(nativeQuery = true, value = "select s.* from sessao s where s.id = ?1")
    Sessao findSessaoByIdSql(Long id);

    @Query("select distinct p.id, p.nome as pauta, p.sessao, "
            + "v.id, v.sessao, v.assossiado, v.simNao "
            + "from Sessao s "
            + "inner join Voto v on v.sessao.id = s.id "
            + "inner join Assossiado a on a.id = v.assossiado.id "
            + "inner join Pauta p on p.id = s.pauta.id "
            + "where s.id = ?1"
    )
    Sessao resultadoVotacao(Long id);

}
