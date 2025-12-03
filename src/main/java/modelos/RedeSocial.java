package modelos;

import jakarta.persistence.*;

@Entity
public class RedeSocial {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nome_plataforma")
	private String nomePlataforma;
	
	private String link;
	
	protected RedeSocial() {}
	
    public RedeSocial(String nomePlataforma, String link){
        this.nomePlataforma = nomePlataforma;
        this.link = link;
    }

    public String getNomePlataforma(){
        return this.nomePlataforma;
    }
    public String getLink(){
        return this.link;
    }
    public void setLink(String link) {
        this.link = link;
    }
}
