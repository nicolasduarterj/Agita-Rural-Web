package modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    private String sobre;

    @Column(name = "publico_alvo")
    private String publicoAlvo;

    @Embedded
    private Local localParaInformacao;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "projeto_id")
    private List<Acao> acoes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "projeto_id")
    private List<Representante> responsaveis = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "projeto_id")
    private List<RedeSocial> perfis = new ArrayList<>();

    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    
    @Column(name = "data_fim")
    private LocalDate dataFim;

    private String emailContato;
    private String celularContato;
    private String linkExterno;

    @ManyToOne
    private Unidade unidade;

    protected Projeto() {}

    public Projeto(String nome, String sobre, String publicoAlvo, Local localParaInformacao,
                   Representante responsavel, String emailContato, String celularContato,
                   String linkExterno, Unidade unidade) {
        this.nome = nome;
        this.sobre = sobre;
        this.publicoAlvo = publicoAlvo;
        this.localParaInformacao = localParaInformacao;
        this.responsaveis.add(responsavel);
        this.dataInicio = LocalDate.now();
        this.emailContato = emailContato;
        this.celularContato = celularContato;
        this.linkExterno = linkExterno;
        this.unidade = unidade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobre() { return sobre; }
    public void setSobre(String sobre) { this.sobre = sobre; }

    public String getPublicoAlvo() { return publicoAlvo; }
    public void setPublicoAlvo(String publicoAlvo) { this.publicoAlvo = publicoAlvo; }

    public Local getLocalParaInformacao() { return localParaInformacao; }
    public void setLocalParaInformacao(Local localParaInformacao) { this.localParaInformacao = localParaInformacao; }

    public List<Acao> getAcoes() { return new ArrayList<>(acoes); }
    public void adicionarAcao(Acao acao) { this.acoes.add(acao); }

    public List<Representante> getResponsaveis() { return new ArrayList<>(responsaveis); }
    public void adicionarResponsavel(Representante r) { this.responsaveis.add(r); }

    public List<RedeSocial> getPerfis() { return new ArrayList<>(perfis); }
    public void adicionarPerfil(RedeSocial perfil) { this.perfis.add(perfil); }

    public LocalDate getDataInicio() { return dataInicio; }

    public String getEmailContato() { return emailContato; }
    public void setEmailContato(String emailContato) { this.emailContato = emailContato; }

    public String getCelularContato() { return celularContato; }
    public void setCelularContato(String celularContato) { this.celularContato = celularContato; }

    public String getLinkExterno() { return linkExterno; }
    public void setLinkExterno(String linkExterno) { this.linkExterno = linkExterno; }

    public Unidade getUnidade() { return unidade; }
    public void setUnidade(Unidade unidade) { this.unidade = unidade; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    
	public void setDataInicio(LocalDate now) {
		this.dataInicio = now;
	}
}
