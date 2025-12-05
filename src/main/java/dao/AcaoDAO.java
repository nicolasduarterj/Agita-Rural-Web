package dao;

import modelos.Acao;
import modelos.Local;
import modelos.Representante;
import modelos.Unidade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import enums.TipoUnidade;

public class AcaoDAO {

    private static final List<Acao> acoes = new ArrayList<>();
    private static int contador = 1; 

    static {
    	List<Representante> rps = new ArrayList<Representante>(Arrays.asList(
    			new Representante("José Carlos", "12345678910", "josecarlos@email.com", "senha")
    			));
    	
    	Unidade departamento = new Unidade("Departamento de Educação Física e Desportos", TipoUnidade.DEPARTAMENTO);
    	
        Acao a1 = new Acao("Tênis", "Venha jogar tênis!", "Alunos",
                new Local("Quadras de Tênis da UFRRJ", "Ufrrj, Seropédica - RJ, 23890-000", ""), LocalDate.now().plusDays(10), LocalDate.now().plusDays(20),
                rps, departamento, false);
        atribuirId(a1);

        Acao a2 = new Acao("Jiu-jitsu", "Aprenda a se defender!", "Alunos",
                new Local("DEFD - Departamento de Educação Física e Desportos", "Ufrrj, Seropédica - RJ, 23890-000", ""), LocalDate.now().plusDays(10), LocalDate.now().plusDays(20),
                rps, departamento, false);
        atribuirId(a2);

        Acao a3 = new Acao("Futebol", "Um clássico brasileiro!", "Alunos",
                new Local("Ginásio", "Ufrrj, Seropédica - RJ, 23890-000", ""), LocalDate.now().plusDays(10), LocalDate.now().plusDays(20),
                rps, departamento, false);
        atribuirId(a3);

        acoes.add(a1);
        acoes.add(a2);
        acoes.add(a3);
    }

    // --- ID ---
    private static void atribuirId(Acao acao) {
        try {
            var idField = Acao.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.setInt(acao, contador++);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao definir ID", e);
        }
    }

    // --- SALVAR AÇÃO ---
    public void salvar(Acao acao) {
        atribuirId(acao);
        acoes.add(acao);
    }

    public List<Acao> listar() {
        return new ArrayList<>(acoes);
    }

    // --- BUSCAR ID ---
    public Acao buscarPorId(int id) {
        return acoes.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public List<Acao> listarRecentes() {
        return acoes.stream()
            .sorted((a1, a2) -> Integer.compare(a2.getId(), a1.getId()))
            .limit(3)
            .collect(Collectors.toList());
    }
    
    public List<Acao> listarComFiltros(String nomeAcao, boolean semTaxa, String[] status){
    	List<Acao> lista = new ArrayList<>();
    	
    	for (Acao acao : acoes) {
    		if (nomeAcao != null)
    			if (!acao.getNome().toLowerCase().contains(nomeAcao.toLowerCase()))
                    continue;
        
    		if (semTaxa) {
    			if (acao.getTaxa())
    				continue;
    		}
    		
    		if (status != null && status.length > 0) {
    			boolean statusBateu = false;

                for (String s : status) {
                    if (acao.getStatus().name().equals(s)) {
                        statusBateu = true;
                        break;
                    }
                }

                if (!statusBateu) {
                    continue;
                }
    		}
    		
    		lista.add(acao);
    	}
    	
    	return lista;	
    }
    // --- ATUALIZAR ---
    public void atualizar(Acao acaoAtualizada) {
        for (int i = 0; i < acoes.size(); i++) {
            if (acoes.get(i).getId() == acaoAtualizada.getId()) {
                acoes.set(i, acaoAtualizada); 
                return;
            }
        }
    }

}
