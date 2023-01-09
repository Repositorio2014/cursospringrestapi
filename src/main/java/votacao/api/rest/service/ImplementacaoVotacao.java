package votacao.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import votacao.api.rest.dto.SessaoDTO;
import votacao.api.rest.model.Assossiado;
import votacao.api.rest.model.Pauta;
import votacao.api.rest.model.Sessao;
import votacao.api.rest.model.Voto;
import votacao.api.rest.repository.AssossiadoRepository;
import votacao.api.rest.repository.PautaRepository;
import votacao.api.rest.repository.SessaoRepository;
import votacao.api.rest.repository.VotoRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
//import votacao.api.rest.repository.VotoRepository;

@Service
public class ImplementacaoVotacao {

    @Autowired
    private AssossiadoRepository assossiadoRepository;

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotoRepository votoRepository;

    public Pauta cadastrarPauta(Pauta pauta){
        Pauta pautaSalva = new Pauta();
        if(pauta != null){
            pautaSalva = this.pautaRepository.save(pauta);
            return pautaSalva;
        }
        return null;
    }

    public Sessao cadastrarSessao(Sessao sessao){

        Sessao sessaoSalva = new Sessao();

        LocalDateTime plusMnutesLater = LocalDateTime.now().plusMinutes(sessao.getDuracao());
        sessao.setDataFim(sessao.getDataFim());
        sessao.setDataFim(plusMnutesLater);

        if(sessao != null){
            sessaoSalva = this.sessaoRepository.save(sessao);
            return sessaoSalva;
        }
        return null;
    }

    public Assossiado cadastrarAssossiado(Assossiado assossiado){
        Assossiado assossiadoSalvo = new Assossiado();
        if(assossiado != null){
            assossiadoSalvo = this.assossiadoRepository.save(assossiado);
            return assossiadoSalvo;
        }
        return null;
    }

    public Voto cadastrarVoto(Voto voto){
        Voto votoSalvo = new Voto();
        LocalDateTime now = LocalDateTime.now();

        if(now.isBefore(voto.getSessao().getDataFim())) {
            if(voto.getAssossiado() != null) {
                if(voto.getSimNao() >=0 && voto.getSimNao() <= 1) {
                    if (voto != null) {
                        votoSalvo = this.votoRepository.save(voto);
                        return votoSalvo;
                    }
                }
            }
        }
        return null;
    }

    public SessaoDTO resultado(Sessao sessao){
        SessaoDTO sessaoDTO;
        List<Voto> votos;
        String inicio;
        String fim;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        if(sessao != null){
            inicio = sessao.getDataInicio().format(formatter);
            fim = sessao.getDataFim().format(formatter);
            votos = votoRepository.findVotosBySessao(sessao.getId());

            try {
                sessaoDTO = new SessaoDTO(sessao, inicio, fim, votos);
                //this.sessaoRepository.resultadoVotacao(sessao.getId());
                return sessaoDTO;
            }catch (Exception e){
                e.printStackTrace();
                e.getMessage();
            }

        }
        return null;
    }

    public Sessao findSessaoById(Long id){
        Sessao sessaoEncontrada = new Sessao();
        if(id > 0){
            //sessaoEncontrada = this.sessaoRepository.findSessaoById(id);
            try {
                sessaoEncontrada = this.sessaoRepository.findSessaoByIdSql(id);
                return sessaoEncontrada;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
