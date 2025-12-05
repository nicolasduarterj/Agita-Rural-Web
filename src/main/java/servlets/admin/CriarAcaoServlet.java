package servlets.admin;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.AcaoDAO;
import dao.UnidadeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelos.Acao;
import modelos.Local;
import modelos.Representante;
import modelos.Unidade;

@WebServlet("/admin/acao/salvar")
public class CriarAcaoServlet extends HttpServlet {

    private AcaoDAO acaoDAO = new AcaoDAO();
    private UnidadeDAO unidadeDAO = new UnidadeDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // ----------------------------------------
        // DADOS PRINCIPAIS
        // ----------------------------------------
        String nome = request.getParameter("nome");
        String sobre = request.getParameter("sobre");
        String publicoAlvo = request.getParameter("publicoAlvo");

        String linkExterno = request.getParameter("linkExterno");
        String emailContato = request.getParameter("email");
        String celularContato = request.getParameter("celular");

        // ----------------------------------------
        // LOCAL
        // ----------------------------------------
        String logradouro = request.getParameter("logradouro");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String pontoReferencia = request.getParameter("pontoReferencia");

        Local local = new Local(logradouro, bairro + ", " + cidade, pontoReferencia);

        // ----------------------------------------
        // LOCAL
        // ----------------------------------------
        
        String dataInicioStr = request.getParameter("dataInicio");
        String dataFimStr = request.getParameter("dataFim");
        
        LocalDate dataInicio = LocalDate.parse(dataInicioStr);
        LocalDate dataFim = LocalDate.parse(dataFimStr);
        
        // ----------------------------------------
        // TAXA / PREÇO
        // ----------------------------------------
        boolean possuiTaxa = request.getParameter("possuiTaxa") != null;
        String preco = request.getParameter("preco");

        // ----------------------------------------
        // UNIDADE
        // ----------------------------------------
        int unidadeId = Integer.parseInt(request.getParameter("unidadeId"));
        Unidade unidade = unidadeDAO.buscarPorId(unidadeId);

        // ----------------------------------------
        // REPRESENTANTE / USUÁRIO LOGADO
        // ----------------------------------------
        HttpSession sessao = request.getSession();
        Representante adminLogado = (Representante) sessao.getAttribute("adminLogado");
        List<Representante> responsaveis = new ArrayList<>();
        responsaveis.add(adminLogado);

        // ----------------------------------------
        // CRIAR AÇÃO
        // ----------------------------------------
        Acao acao = new Acao(
                nome,
                sobre,
                publicoAlvo,
                local,
                dataInicio,
                dataFim,
                responsaveis,
                unidade,
                possuiTaxa
        );

        acao.setLinkExterno(linkExterno);
        acao.setEmail(emailContato);
        acao.setCelular(celularContato);

        if (possuiTaxa) {
            acao.setPreco(preco);
        }

        // ----------------------------------------
        // SALVAR
        // ----------------------------------------
        acaoDAO.salvar(acao);

        // Redireciona para o painel de controle
        response.sendRedirect(request.getContextPath() + "/admin/painelDeControle.jsp");
    }
}
