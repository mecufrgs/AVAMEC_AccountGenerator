package edu.avamec.accountgenerator.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="usuario")
public class Usuario {
    private static final String SEQUENCE_NAME = "usuario_seq";

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "checked")
    private Boolean checked;

    @Basic(optional = false)
    @Column(name = "dataNascimento", length = 30)
    private String dataNascimento;

    @Basic(optional = false)
    @Column(name = "email", length = 50)
    private String email;

    @Basic(optional = false)
    @Column(name = "emailConfirmacao", length = 50)
    private String emailConfirmacao;

    @Basic(optional = false)
    @Column(name = "estrangeiro")
    private Boolean estrangeiro;

    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "municipio")
    private Municipio municipio;

    @Basic(optional = false)
    @Column(name = "nomeCompleto", length = 30)
    private String nomeCompleto;

    @Basic(optional = false)
    @Column(name = "nomeSocial", length = 30)
    private String nomeSocial;

    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "pais")
    private Pais pais;

    @Basic(optional = false)
    @Column(name = "receberSMS")
    private Boolean receberSMS;

    @Basic(optional = false)
    @Column(name = "selecionado")
    private Boolean selecionado;

    @Basic(optional = false)
    @Column(name = "senha", length = 64)
    private String senha;

    @Basic(optional = false)
    @Column(name = "senhaConfirmacao", length = 64)
    private String senhaConfirmacao;

    @Basic(optional = false)
    @Column(name = "sexo", length = 15)
    private String sexo;

    @Basic(optional = false)
    @Column(name = "situacao", length = 15)
    private String situacao;

    @OneToMany(mappedBy = "usuario")
    private List<Telefone> telefones;

    @JsonIgnore
    @Basic(optional = false)
    @Column(name = "nomeGerador", length = 30)
    private String nomeGerador;

    @JsonIgnore
    @Basic(optional = false)
    @Column(name = "numeroGerador")
    private Long numeroGerador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailConfirmacao() {
        return emailConfirmacao;
    }

    public void setEmailConfirmacao(String emailConfirmacao) {
        this.emailConfirmacao = emailConfirmacao;
    }

    public Boolean getEstrangeiro() {
        return estrangeiro;
    }

    public void setEstrangeiro(Boolean estrangeiro) {
        this.estrangeiro = estrangeiro;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeSocial() {
        return nomeSocial;
    }

    public void setNomeSocial(String nomeSocial) {
        this.nomeSocial = nomeSocial;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Boolean getReceberSMS() {
        return receberSMS;
    }

    public void setReceberSMS(Boolean receberSMS) {
        this.receberSMS = receberSMS;
    }

    public Boolean getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaConfirmacao() {
        return senhaConfirmacao;
    }

    public void setSenhaConfirmacao(String senhaConfirmacao) {
        this.senhaConfirmacao = senhaConfirmacao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String getNomeGerador() {
        return nomeGerador;
    }

    public void setNomeGerador(String nomeGerador) {
        this.nomeGerador = nomeGerador;
    }

    public Long getNumeroGerador() {
        return numeroGerador;
    }

    public void setNumeroGerador(Long numeroGerador) {
        this.numeroGerador = numeroGerador;
    }
}
