package servlets.publico;

import dao.ProjetoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelos.Projeto;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProjetoDAO projetoDAO = new ProjetoDAO();

        Projeto principal = projetoDAO.buscarPrincipal();
        
        request.setAttribute("projetoPrincipal", principal);
        request.setAttribute("acoes", principal.getAcoes()); 

        request.getRequestDispatcher("/publico/home.jsp").forward(request, response);
    }
}
