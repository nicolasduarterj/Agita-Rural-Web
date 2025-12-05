package servlets.publico;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelos.Acao;

import java.io.IOException;
import java.util.List;

import dao.AcaoDAO;

@WebServlet("/acoes")
public class AcoesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AcoesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeAcao = (String) request.getParameter("nomeAcao");
		String possuiTaxa = (String) request.getParameter("semTaxa");
		String[] status = request.getParameterValues("status");
		
		AcaoDAO acaoDAO = new AcaoDAO();
		
		boolean semTaxa = (possuiTaxa != null && possuiTaxa == "true") ? true : false;
		nomeAcao = (nomeAcao != null && !nomeAcao.trim().isEmpty()) ? nomeAcao : null;
		
		List<Acao> acoes = acaoDAO.listarComFiltros(nomeAcao, semTaxa, status);
		
		request.setAttribute("acoes", acoes);
		
		request.getRequestDispatcher("publico/acoes.jsp").forward(request, response);
	}
}
