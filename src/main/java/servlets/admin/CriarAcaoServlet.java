package servlets.admin;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.AcaoDAO;
import dao.UnidadeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelos.Acao;
import modelos.Local;
import modelos.Representante;
import modelos.Unidade;

@WebServlet("/admin/salvarAcao")
public class CriarAcaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // ----------------------------------------
        // DADOS BASE
        // ----------------------------------------
        String nome = request.getParameter("nome");
        String sobre = request.getParameter("sobre");
        String publicoAlvo = request.getParameter("publicoAlvo");


        // ----------------------------------------
        // LOCALIZAÇÃO
        // ----------------------------------------
        String logradouro = request.getParameter("logradouro");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String pontoReferencia = request.getParameter("pontoReferencia"); // mesmo nome do input
        String nomeLocal = logradouro;
        String enderecoCompleto = logradouro + ", " + bairro + ", " + cidade;
        if (pontoReferencia != null && !pontoReferencia.isEmpty()) {
            enderecoCompleto += " - " + pontoReferencia;
        }

        Local local = new Local(nomeLocal, enderecoCompleto, pontoReferencia);

     // ----------------------------------------
        // DATAS
        // ----------------------------------------
        String dataInicioStr = request.getParameter("dataInicio");
        String dataFimStr = request.getParameter("dataFim");
        
        LocalDateTime dataInicio = LocalDateTime.parse(dataInicioStr);
        LocalDateTime dataFim = LocalDateTime.parse(dataFimStr);
        
        // ----------------------------------------
        // TAXA | PREÇO
        // ----------------------------------------
        boolean possuiTaxa = request.getParameter("possuiTaxa") != null;
        String preco = request.getParameter("preco");


        // ----------------------------------------
        // UNIDADE
        // ----------------------------------------
        int unidadeId = Integer.parseInt(request.getParameter("unidadeId"));
        UnidadeDAO unidadeDAO = new UnidadeDAO();
        Unidade unidade = unidadeDAO.buscarPorId(unidadeId);


        // ----------------------------------------
        // REPRESENTANTE
        // ----------------------------------------
        HttpSession sessao = request.getSession();
        Representante adminLogado = (Representante) sessao.getAttribute("adminLogado");

        List<Representante> reps = new ArrayList<>();
        reps.add(adminLogado);

        Acao acao = new Acao(
                nome,
                sobre,
                publicoAlvo,
                local,
                dataInicio,
                dataFim,
                reps,
                unidade,
                possuiTaxa
        );

        if (possuiTaxa) {
            acao.setPreco(preco);
        }

        // ----------------------------------------
        // SALVAR NO DAO
        // ----------------------------------------
        AcaoDAO acaoDAO = new AcaoDAO();
        acaoDAO.salvar(acao);

        response.sendRedirect(request.getContextPath() + "/admin/painelDeControle.jsp");
    }
}
