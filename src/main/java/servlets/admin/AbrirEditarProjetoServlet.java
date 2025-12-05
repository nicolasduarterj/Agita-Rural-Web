package servlets.admin;

import dao.ProjetoDAO;
import dao.UnidadeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelos.Projeto;

import java.io.IOException;

@WebServlet("/admin/projetoMod")
public class AbrirEditarProjetoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        if (idParam == null) {
            request.setAttribute("erro", "ID do projeto não informado.");
            request.getRequestDispatcher("/admin/projetoListar.jsp").forward(request, response);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            request.setAttribute("erro", "ID do projeto inválido.");
            request.getRequestDispatcher("/admin/projetoListar.jsp").forward(request, response);
            return;
        }

        ProjetoDAO projetoDAO = new ProjetoDAO();
        Projeto projeto = projetoDAO.getPorId(id);

        if (projeto == null) {
            request.setAttribute("erro", "Projeto não encontrado.");
            request.getRequestDispatcher("/admin/projetoListar.jsp").forward(request, response);
            return;
        }

        request.setAttribute("unidades", new UnidadeDAO().listar());
        request.setAttribute("projeto", projeto);

        request.getRequestDispatcher("/admin/projetoMod.jsp").forward(request, response);
    }
}
