package servlets.admin;

import dao.ProjetoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelos.Projeto;
import modelos.Representante;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/admin/projetoListar")
public class ListarProjetosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sessao = request.getSession();
        Representante admin = (Representante) sessao.getAttribute("adminLogado");

        if (admin == null) {
            response.sendRedirect(request.getContextPath() + "/admin/loginadmin.jsp");
            return;
        }

        ProjetoDAO projetoDAO = new ProjetoDAO();
        List<Projeto> projetos = projetoDAO.listar()
                .stream()
                .filter(p -> p.getResponsaveis() != null &&
                             p.getResponsaveis()
                              .stream()
                              .filter(r -> r != null) 
                              .anyMatch(r -> r.getId() == admin.getId()))
                .collect(Collectors.toList());

        request.setAttribute("projetos", projetos);
        request.getRequestDispatcher("/admin/projetoListar.jsp")
               .forward(request, response);
    }
}
