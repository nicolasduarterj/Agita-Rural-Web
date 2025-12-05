package dao;

import modelos.Acao;
import modelos.Local;
import modelos.Projeto;
import modelos.RedeSocial;
import modelos.Representante;
import modelos.Unidade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.TipoUnidade;

public class ProjetoDAO {

    private static final List<Projeto> projetos = new ArrayList<>();

    static {
        AcaoDAO acaoDAO = new AcaoDAO();
        
        List<Acao> acoes = acaoDAO.listarRecentes();

        // Responsáveis de exemplo
        Representante rep1 = new Representante("João Silva", null, "joao@email.com", null);
        Representante rep2 = new Representante("Maria Souza", null, "maria@email.com", null);

        // Perfis de redes sociais de exemplo
        RedeSocial perfil1 = new RedeSocial("Instagram", "https://instagram.com/esporte");
        RedeSocial perfil2 = new RedeSocial("Facebook", "https://facebook.com/esporte");

        // Unidades de exemplo
        Unidade unidade1 = new Unidade(4, "Departamento da Computação", TipoUnidade.DEPARTAMENTO);
        Unidade unidade2 = new Unidade(5, "Instituto da fazenda", TipoUnidade.INSTITUTO);

        // Projetos
        Projeto principal = new Projeto(
                "Esporte na Rural",
                "Vários esportes dentro da Universidade!",
                "Alunos",
                new Local("DEFIS", "Rural", "Próximo ao ginásio"),
                rep1,
                "contato@esporte.com",
                "1234-5678",
                "https://instagram.com/esporte",
                unidade1
        );

        Projeto projeto2 = new Projeto(
                "Idiomas sem fronteiras",
                "Cursos de linguagens",
                "Alunos",
                new Local("Pavilhão de aulas teóricas", "UFRRJ", "PAT"),
                rep2,
                "idiomas@ufrrj.com",
                "9876-5432",
                "https://instagram.com/idiomas",
                unidade2
        );

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

    public List<Projeto> listar() {
        return new ArrayList<>(projetos);
    }

    public List<Projeto> buscarPorNome(String nomeProjeto) {
        List<Projeto> lista = new ArrayList<>();
        if (nomeProjeto == null) return lista;

        String nomeParaBusca = nomeProjeto.toLowerCase();
        for (Projeto projeto : projetos)
            if (projeto.getNome().toLowerCase().contains(nomeParaBusca))
                lista.add(projeto);

        return lista;
    }

    public void salvar(Projeto projeto) {
        projeto.setId(projetos.size() + 1);
        projetos.add(projeto);
    }

    public void atualizar(Projeto projetoAtualizado) {
        for (int i = 0; i < projetos.size(); i++) {
            if (projetos.get(i).getId() == projetoAtualizado.getId()) {
                projetos.set(i, projetoAtualizado);
                break;
            }
        }
    }
    public void encerrarProjeto(Projeto projeto) {
        projeto.setDataFim(LocalDate.now()); 
    }
}
