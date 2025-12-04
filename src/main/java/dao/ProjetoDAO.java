package dao;

import modelos.Acao;
import modelos.Local;
import modelos.Projeto;

import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    private static final List<Projeto> projetos = new ArrayList<>();

    static {
        AcaoDAO acaoDAO = new AcaoDAO();
        
        List<Acao> acoes = acaoDAO.listarRecentes();

        Projeto principal = new Projeto(
                "Esporte na Rural",
                "Vários esportes dentro da Universidade!",
                "Alunos",
                new Local("DEFIS", "Rural", "")
        );
        
        Projeto projeto2 = new Projeto(
        		"Idiomas sem fronteiras",
        		"Cursos de linguagens",
        		"Alunos",
        		new Local("Pavilhão de aulas teóricas", "UFRRJ", "PAT"));

        principal.setId(1);
        projeto2.setId(2);
        
        
        for (Acao acao : acoes)
        	principal.adicionarAcao(acao);
        
        projetos.add(principal);
        projetos.add(projeto2);
    }

    public Projeto buscarPrincipal() {
        return projetos.get(0);
    }
    
    public Projeto getPorId(int id) {
    	for (Projeto projeto : projetos)
    		if (projeto.getId() == id)
    			return projeto;
    	
    	return null;
    }
    public List<Projeto> listar(){
    	return new ArrayList<>(projetos);
    }
    public List<Projeto> buscarPorNome(String nomeProjeto){
    	List<Projeto> lista = new ArrayList<>();
    	
    	if (nomeProjeto == null)
    		return lista;
    	
    	String nomeParaBusca = nomeProjeto.toLowerCase();
    	
    	for (Projeto projeto : projetos)
    		if (projeto.getNome().toLowerCase().contains(nomeParaBusca))
    			lista.add(projeto);
    	
    	return lista;
    }
}
