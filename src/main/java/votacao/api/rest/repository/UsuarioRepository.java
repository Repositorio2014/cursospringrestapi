package votacao.api.rest.repository;

import votacao.api.rest.model.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query("select u from Usuario u where u.login = ?1")
    Usuario findUserByLogin(String login);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update usuario set token = ?1 where login = ?2")
    void atualizaTokenUsuario(String token, String login);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into usuarios_role(usuario_id, role_id) values(?1, ?2)")
    void salvarUsuariosRole(Long user_id, Long role_id);

}
