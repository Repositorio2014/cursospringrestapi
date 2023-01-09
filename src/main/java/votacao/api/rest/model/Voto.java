package votacao.api.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    private Sessao sessao;

    @OneToOne
    private Assossiado assossiado;

    private int simNao;

    public Voto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Assossiado getAssossiado() {
        return assossiado;
    }

    public void setAssossiado(Assossiado assossiado) {
        this.assossiado = assossiado;
    }

    public int getSimNao() {
        return simNao;
    }

    public void setSimNao(int simNao) {
        this.simNao = simNao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto = (Voto) o;
        return simNao == voto.simNao && Objects.equals(id, voto.id) && Objects.equals(sessao, voto.sessao) && Objects.equals(assossiado, voto.assossiado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessao, assossiado, simNao);
    }
}
