package servlets.admin;

import dao.AcaoDAO;
import dao.ProjetoDAO;
import dao.UnidadeDAO;
import modelos.Acao;
import modelos.Local;
import modelos.Projeto;
import modelos.Representante;
import modelos.Unidade;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/admin/projeto/salvar")
public class CriarProjetoServlet extends HttpServlet {

    private ProjetoDAO projetoDAO = new ProjetoDAO();
    private UnidadeDAO unidadeDAO = new UnidadeDAO();
    private AcaoDAO acaoDAO = new AcaoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String sobre = request.getParameter("sobre");
        String publicoAlvo = request.getParameter("publicoAlvo");

        String nomeLocal = request.getParameter("nomeLocal");
        String bairroCidade = request.getParameter("bairroCidade");
        String pontoReferencia = request.getParameter("pontoReferencia");

        String emailContato = request.getParameter("emailContato");
        String celularContato = request.getParameter("celularContato");
        String linkExterno = request.getParameter("linkExterno");

        int unidadeId = Integer.parseInt(request.getParameter("unidadeId"));
        String[] acoesSelecionadasIds = request.getParameterValues("acoesSelecionadas");

        Representante usuarioLogado = (Representante) request.getSession().getAttribute("usuarioLogado");

        Local local = new Local(nomeLocal, bairroCidade, pontoReferencia);

        Unidade unidade = unidadeDAO.buscarPorId(unidadeId);
        Projeto projeto = new Projeto(
                nome,
                sobre,
                publicoAlvo,
                local,
                usuarioLogado,
                emailContato,
                celularContato,
                linkExterno,
                unidade
        );
        
        projeto.setDataInicio(LocalDate.now());

        if (acoesSelecionadasIds != null) {
            for (String acaoIdStr : acoesSelecionadasIds) {
                int acaoId = Integer.parseInt(acaoIdStr);
                Acao acao = acaoDAO.buscarPorId(acaoId);
                if (acao != null) {
                    projeto.adicionarAcao(acao);
                }
            }
        }

        projetoDAO.salvar(projeto);

        response.sendRedirect(request.getContextPath() + "/admin/painelDeControle.jsp");
    }
}
