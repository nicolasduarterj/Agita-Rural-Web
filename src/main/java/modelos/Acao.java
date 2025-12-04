package modelos;

import enums.Status;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Acao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    @JoinColumn(name = "acao_id")
    private List<RedeSocial> perfis = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "representantes_acao",
            joinColumns = @JoinColumn(name = "acao_id"),
            inverseJoinColumns = @JoinColumn(name = "representante_id")
    )
    private List<Representante> representantes;

    private String nome;

    private String sobre;

    @Column(name = "publico_alvo")
    private String publicoAlvo;

    @Column(name = "possui_taxa")
    private boolean possuiTaxa;

    private String preco;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_final")
    private LocalDateTime dataFim;

    @Embedded
    private Local localizacao;

    @ManyToOne
    private Unidade unidade;

    @Enumerated(EnumType.STRING)
    private Status status;

    protected Acao() {}

    public Acao(String nome, String sobre, String publicoAlvo, Local localizacao,
                List<Representante> representantes, Unidade unidade, boolean possuiTaxa) {

        this.nome = nome;
        this.sobre = sobre;
        this.publicoAlvo = publicoAlvo;
        this.localizacao = localizacao;
        this.representantes = representantes != null ? representantes : new ArrayList<>();
        this.unidade = unidade;
        this.possuiTaxa = possuiTaxa;

        this.dataInicio = LocalDateTime.now();
        this.status = Status.ATIVO;

        this.perfis = new ArrayList<>();

        if (!possuiTaxa) {
            this.preco = "GRATUITO";
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobre() {
        return sobre;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public List<RedeSocial> getPerfis() {
        return new ArrayList<>(perfis);
    }

    public Local getLocalizacao() {
        return localizacao;
    }
    
    private String formatarData(LocalDateTime data) {
    	if (data == null)
    		return "";
    	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	return data.format(dtf);
    }
    
    public String getDataInicioFormatada() {
    	return formatarData(this.dataInicio);
    }
    
    public String getDataFimFormatada() {
    	return formatarData(this.dataFim);
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public List<Representante> getRepresentantes() {
        return new ArrayList<>(representantes);
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Status getStatus() {
        return status;
    }

    public boolean getTaxa() {
        return possuiTaxa;
    }

    public String getPreco() {
        return preco;
    }

    public void setId(int id) {
    	this.id = id;
    }
    
    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public void setLocalizacao(Local localizacao) {
        this.localizacao = localizacao;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void adicionarPerfil(RedeSocial perfil) {
        this.perfis.add(perfil);
    }

    public void adicionarRepresentante(Representante representante) {
        this.representantes.add(representante);
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public void setTaxa(boolean possuiTaxa) {
        this.possuiTaxa = possuiTaxa;

        if (!possuiTaxa) {
            this.preco = "GRATUITO";
        }
    }

    public void setPreco(String preco) {
        if (!this.possuiTaxa) {
            this.preco = "GRATUITO";
        } else {
            this.preco = preco;
        }
    }
}
