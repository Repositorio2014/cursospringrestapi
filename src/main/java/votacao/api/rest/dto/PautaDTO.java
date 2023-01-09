package votacao.api.rest.dto;

import java.io.Serializable;

public class PautaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    public PautaDTO() {
    }

    public PautaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
