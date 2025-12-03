package modelos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Projeto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String nome;
	
	private String sobre;
	
	@Column(name = "publico_alvo")
    private String publicoAlvo;
    
    @Embedded
	private Local localParaInformacao;
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "projeto_id")
	private List<Acao> acoes = new ArrayList<>();
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "projeto_id")
	private List<RedeSocial> perfis = new ArrayList<>();

	protected Projeto() {}
	
    public Projeto(String nome, String sobre, String publicoAlvo, Local localParaInformacao){
        this.nome = nome;
        this.sobre = sobre;
        this.publicoAlvo = publicoAlvo;
        this.localParaInformacao = localParaInformacao;
    }

    public String getNome() {
        return nome;
    }
    public String getSobre() {
        return sobre;
    }
    public String getPublicoAlvo() { return publicoAlvo; }
    public Local getLocalParaInformacao() {
        return localParaInformacao;
    }
    public void adicionarAcao(Acao acao) {
        this.acoes.add(acao);
    }
    public List<Acao> getAcoes() {
        return new ArrayList<>(acoes);
    }
    public void adicionarPerfil(RedeSocial perfil) {
        this.perfis.add(perfil);
    }
    public List<RedeSocial> getPerfis() {
        return new ArrayList<>(perfis);
    }


    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSobre(String sobre) {
        this.sobre = sobre;
    }
    public void setPublicoAlvo(String publicoAlvo) { this.publicoAlvo = publicoAlvo; }
    public void setLocalParaInformacao(Local localParaInformacao) {
        this.localParaInformacao = localParaInformacao;
    }

}

