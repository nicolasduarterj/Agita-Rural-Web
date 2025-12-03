package modelos;

import jakarta.persistence.*;

@Embeddable
public class Local {
	@Column(name = "local_nome", length = 100)
	private String nome;
	
	@Column(name = "local_endereco")
	private String endereco;
	
	@Column(name = "ponto_referencia")
	private String pontoDeReferencia;
	
	protected Local() {}
	
    public Local(String nome, String endereco, String pontoDeReferencia){
        this.nome = nome;
        this.endereco = endereco;
        this.pontoDeReferencia = pontoDeReferencia;
    }

    public String getNome() {
        return nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public String getPontoDeReferencia() {
        return pontoDeReferencia;
    }
}