package curso.api.rest.controller;

import curso.api.rest.dto.UsuarioDTO;
import curso.api.rest.model.Endereco;
import curso.api.rest.model.Usuario;
import curso.api.rest.repository.EnderecoRepository;
import curso.api.rest.repository.UsuarioRepository;
import org.hibernate.tool.hbm2ddl.UniqueConstraintSchemaUpdateStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario")
public class IndexController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UsuarioDTO> init(@PathVariable (value = "id") Long id){

        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return new ResponseEntity<UsuarioDTO>(new UsuarioDTO(usuario.get()), HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = "application/json")
    @CacheEvict(value = "cacheusuarios", allEntries = true)
    @CachePut("cacheusuarios")
    public ResponseEntity<List<Usuario>> usuarios() throws InterruptedException {
        List<Usuario> usuarioList = (List<Usuario>) usuarioRepository.findAll();

        /*Teste de cache*/
        //Thread.sleep(6000);

        return new ResponseEntity<List<Usuario>>(usuarioList, HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){

        for(int pos = 0; pos < usuario.getTelefones().size(); pos ++){
            usuario.getTelefones().get(pos).setUsuario(usuario);
        }
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        Endereco endereco = new Endereco();
        endereco.setCep(usuarioSalvo.getEndereco().getCep());
        endereco.setLogradouro(usuario.getEndereco().getLogradouro());
        endereco.setComplemento(usuario.getEndereco().getComplemento());
        endereco.setBairro(usuario.getEndereco().getBairro());
        endereco.setLocalidade(usuario.getEndereco().getLocalidade());
        endereco.setUf(usuario.getEndereco().getUf());
        endereco.setUsuario(usuario);

        Endereco enderecoSalvo = enderecoRepository.save(endereco);

        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

    @PostMapping(value = "/vendausuario", produces = "application/json")
    public ResponseEntity<Usuario> cadastrarVenda(@RequestBody Usuario usuario){

        for(int pos = 0; pos < usuario.getTelefones().size(); pos ++){
            usuario.getTelefones().get(pos).setUsuario(usuario);
        }
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario){

        for(int pos = 0; pos < usuario.getTelefones().size(); pos ++){
            usuario.getTelefones().get(pos).setUsuario(usuario);
        }

        Usuario userTepodrario = usuarioRepository.findUserByLogin(usuario.getLogin());

        if(!userTepodrario.getSenha().equals(usuario.getSenha())){ /*senhas diferentes*/
            String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
            usuario.setSenha(senhaCriptografada);
        }

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public String delete(@PathVariable (value = "id") Long id){

        usuarioRepository.deleteById(id);
        return ("OK!");
    }
}
