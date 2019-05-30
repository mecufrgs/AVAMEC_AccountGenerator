package edu.avamec.accountgenerator.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="telefone")
public class Telefone {
    private static final String SEQUENCE_NAME = "telefone_seq";

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "ddi", length = 5)
    private String ddi;

    @Basic(optional = false)
    @Column(name = "tipoTelefone", length = 10)
    private String tipoTelefone;

    @Basic(optional = false)
    @Column(name = "numero", length = 20)
    private String numero;

    @JsonIgnore
    @ManyToOne
    @Basic(optional = false)
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdi() {
        return ddi;
    }

    public void setDdi(String ddi) {
        this.ddi = ddi;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
