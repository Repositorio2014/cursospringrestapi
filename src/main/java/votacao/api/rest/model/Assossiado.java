package votacao.api.rest.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Assossiado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String nome;

    /*@ManyToMany(cascade = CascadeType.ALL)
            @JoinTable(name = "sessao_assossiado",
                       joinColumns ={@JoinColumn(name = "sessao_id")},
                       inverseJoinColumns ={@JoinColumn(name = "assossiado_id")})
    List<Sessao> sessoes;*/

    public Assossiado() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assossiado that = (Assossiado) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) /*&& Objects.equals(sessoes, that.sessoes)*/;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome/*, sessoes*/);
    }
}
