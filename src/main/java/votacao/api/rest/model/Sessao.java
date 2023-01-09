package votacao.api.rest.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "pauta_id", referencedColumnName = "id")
    private Pauta pauta;

    private LocalDateTime dataInicio = LocalDateTime.now();
    private LocalDateTime dataFim;
    private int duracao;

    /*@ManyToMany(mappedBy = "sessoes", cascade = CascadeType.ALL)
    private List<Assossiado> assossiados = new ArrayList<>();*/

    public Sessao() {
    }

    public Sessao(Pauta pauta, int duracao) {
        this.pauta = pauta;
        this.duracao = duracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sessao sessao = (Sessao) o;
        return duracao == sessao.duracao && Objects.equals(id, sessao.id) && Objects.equals(pauta, sessao.pauta) && Objects.equals(dataInicio, sessao.dataInicio) && Objects.equals(dataFim, sessao.dataFim)/* && Objects.equals(assossiados, sessao.assossiados)*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pauta, dataInicio, dataFim, duracao /*, assossiados*/);
    }
}
