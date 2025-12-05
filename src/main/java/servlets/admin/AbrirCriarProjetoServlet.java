package servlets.admin;

import dao.AcaoDAO;
import dao.UnidadeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Acao;
import modelos.Unidade;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/projeto/criar")
public class AbrirCriarProjetoServlet extends HttpServlet {

    private UnidadeDAO unidadeDAO = new UnidadeDAO();
    private AcaoDAO acaoDAO = new AcaoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Unidade> unidades = unidadeDAO.listar();
        request.setAttribute("unidades", unidades);

        List<Acao> acoes = acaoDAO.listar();
        request.setAttribute("acoes", acoes);

        request.getRequestDispatcher("/admin/projetoCriar.jsp")
               .forward(request, response);
    }
}
