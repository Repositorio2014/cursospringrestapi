package votacao.api.rest.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import votacao.api.rest.dto.SessaoDTO;
import votacao.api.rest.dto.UsuarioDTO;
import votacao.api.rest.model.Assossiado;
import votacao.api.rest.model.Pauta;
import votacao.api.rest.model.Sessao;
import votacao.api.rest.model.Usuario;
import votacao.api.rest.model.Voto;
import votacao.api.rest.service.ImplementacaoVotacao;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/assembleia")
@Api(value = "Api para gerenciar sessões de votação", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, tags = {"Assembleia"})
public class VotacaoController {

    @Autowired
    private ImplementacaoVotacao implementacaoVotacao;

    @PostMapping(value = "/pauta/", produces = "application/json")
    @ApiOperation(value = "Cria uma pauta", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pauta> cadastrar(@RequestBody Pauta pauta){

        Pauta pautaSalva = new Pauta();

        if(pauta != null){
            pautaSalva = this.implementacaoVotacao.cadastrarPauta(pauta);
        }
        return new ResponseEntity<Pauta>(pautaSalva, HttpStatus.OK);
    }

    @PostMapping(value = "/sessao/", produces = "application/json")
    @ApiOperation(value = "Cria uma sessão", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sessao> cadastrarSessao(@RequestBody Sessao sessao){

        Sessao sessaoSalva = new Sessao();

        if(sessao != null){
            sessaoSalva = this.implementacaoVotacao.cadastrarSessao(sessao);
        }
        return new ResponseEntity<Sessao>(sessaoSalva, HttpStatus.OK);
    }

    @PostMapping(value = "/assossiado/", produces = "application/json")
    @ApiOperation(value = "Cadastrar um assossiado", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Assossiado> cadastrarAssossiado(@RequestBody Assossiado assossiado){

        Assossiado assossiadoSalvo = new Assossiado();

        if(assossiado != null){
            assossiadoSalvo = this.implementacaoVotacao.cadastrarAssossiado(assossiado);
        }
        return new ResponseEntity<Assossiado>(assossiadoSalvo, HttpStatus.OK);
    }

    @PostMapping(value = "/cadastrarVoto/", produces = "application/json")
    @ApiOperation(value = "Cadastrar/computar um voto - votar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Voto> cadastrarVoto(@RequestBody Voto voto){

        Voto votoSalvo = new Voto();

        if(voto != null){
            votoSalvo = this.implementacaoVotacao.cadastrarVoto(voto);
        }
        return new ResponseEntity<Voto>(votoSalvo, HttpStatus.OK);
    }

    @PostMapping(value = "/resultadoVotacao/", produces = "application/json")
    @ApiOperation(value = "Consultar resultado da votação", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String resultadoVotacao(@RequestBody Sessao sessao){

        try {
            SessaoDTO sessaoDTO = this.implementacaoVotacao.resultado(sessao);
            Gson gson = new Gson();
            String json = gson.toJson(sessaoDTO);
            return json;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/findSessaoById/{id}", produces = "application/json")
    @ApiOperation(value = "Consultar uma sessão pelo Id", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sessao> findSessaoById(@PathVariable (value = "id") Long id){

        Sessao sessao = this.implementacaoVotacao.findSessaoById(id);
        return new ResponseEntity<Sessao>(sessao, HttpStatus.OK);
    }

}
