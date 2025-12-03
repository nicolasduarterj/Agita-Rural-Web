package modelos;

import enums.TipoUnidade;
import jakarta.persistence.*;

@Entity
public class Unidade {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private final String nome;
	
	@Enumerated(EnumType.STRING)
	private TipoUnidade tipo;

    public Unidade(String nome, TipoUnidade tipo){
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }
    public TipoUnidade getTipo() {
        return tipo;
    }
}

