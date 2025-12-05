package servlets.publico;

import dao.AcaoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelos.Acao;

import java.io.IOException;

@WebServlet("/acao")
public class AcaoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da ação não informado.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
            return;
        }

        AcaoDAO acaoDAO = new AcaoDAO();
        Acao acao = acaoDAO.buscarPorId(id);
        if (acao == null) {
        	response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação não encontrada.");
            return;
        }
        	
        
        request.setAttribute("acao", acao);

        request.getRequestDispatcher("/publico/acao.jsp").forward(request, response);
    }
}
