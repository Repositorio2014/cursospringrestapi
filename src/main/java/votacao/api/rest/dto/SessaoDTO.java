package votacao.api.rest.dto;

import votacao.api.rest.model.Sessao;
import votacao.api.rest.model.Voto;

import java.io.Serializable;
import java.util.List;

public class SessaoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private PautaDTO pauta;
    private String inicio;
    private String fim;
    private int duracao;
    List<Voto> listaDeVotos;

    public SessaoDTO(Sessao sessao, String inicio, String fim, List<Voto> votosList) {
        PautaDTO pautaDTO = new PautaDTO();
        pautaDTO.setNome(sessao.getPauta().getNome());

        this.pauta = pautaDTO;
        this.inicio = inicio;
        this.fim = fim;
        this.duracao = sessao.getDuracao();
        this.listaDeVotos = votosList;

    }

    public SessaoDTO(SessaoDTO sessaoDTO) {
        this.pauta = sessaoDTO.pauta;
        this.inicio = sessaoDTO.inicio;
        this.fim = sessaoDTO.fim;
        this.duracao = sessaoDTO.duracao;
        this.listaDeVotos = sessaoDTO.listaDeVotos;
    }
}
