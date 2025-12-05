package servlets.admin;

import dao.AcaoDAO;
import enums.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelos.Acao;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/admin/encerrarAcao")
public class EncerrarAcaoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        AcaoDAO acaoDAO = new AcaoDAO();
        Acao acao = acaoDAO.buscarPorId(id);

        if (acao == null) {
            request.setAttribute("erro", "Ação não encontrada.");
            request.getRequestDispatcher("/admin/acaoListar.jsp").forward(request, response);
            return;
        }

        acao.setStatus(Status.DESCONTINUADO);
        acao.setDataFim(LocalDate.now());

        acaoDAO.atualizar(acao);

        response.sendRedirect(request.getContextPath() + "/admin/acaoListar");
    }
}
