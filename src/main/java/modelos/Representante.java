package modelos;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Representante {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nome_completo")
	private String nomeCompleto;
	
	private String cpf;
	
	@Column(name = "email_contato")
	private String emailContato;
	
	@ManyToMany
	@JoinTable(
			name = "representante_projeto",
			joinColumns = @JoinColumn(name = "representante_id"),
			inverseJoinColumns = @JoinColumn(name = "projeto_id")
	)
    private List<Projeto> projetos;
	
	@ManyToMany(mappedBy = "representantes")
    private List<Acao> acoes;
	
    protected Representante() {}
    
    public Representante(String nomeCompleto, String cpf, String emailContato){
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.emailContato = emailContato;
        this.projetos = new ArrayList<>();
        this.acoes = new ArrayList<>();
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmailContato() {
        return emailContato;
    }
    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }
    public List<Projeto> getProjetos() {
        return new ArrayList<>(projetos);
    }
    public void adicionarProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }
    public List<Acao> getAcoes() {
        return new ArrayList<>(acoes);
    }
    public void adicionarAcao(Acao acao) {
        this.acoes.add(acao);
    }
}
