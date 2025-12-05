package servlets.admin;

import dao.ProjetoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelos.Projeto;

import java.io.IOException;

@WebServlet("/admin/projeto/encerrar")
public class EncerrarProjetoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        ProjetoDAO dao = new ProjetoDAO();
        Projeto projeto = dao.getPorId(id);

        if (projeto != null) {
            dao.encerrarProjeto(projeto);
        }

        response.sendRedirect(request.getContextPath() + "/admin/projetoListar");
    }
}
