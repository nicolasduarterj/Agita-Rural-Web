package servlets.admin;

import dao.ProjetoDAO;
import dao.UnidadeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelos.Projeto;
import modelos.Unidade;

import java.io.IOException;

@WebServlet("/admin/projetoSalvar")
public class EditarProjetoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // Obter ID do projeto
        int id = Integer.parseInt(request.getParameter("id"));

        ProjetoDAO projetoDAO = new ProjetoDAO();
        Projeto projeto = projetoDAO.getPorId(id);

        if (projeto == null) {
            request.setAttribute("erro", "Projeto não encontrado para edição.");
            request.getRequestDispatcher("/admin/projetoListar.jsp").forward(request, response);
            return;
        }

        // Campos principais
        String nome = request.getParameter("nome");
        String sobre = request.getParameter("sobre");
        String publicoAlvo = request.getParameter("publicoAlvo");

        // Unidade
        int unidadeId = Integer.parseInt(request.getParameter("unidade"));
        Unidade unidade = new UnidadeDAO().buscarPorId(unidadeId);

        // Contato e link
        String emailContato = request.getParameter("emailContato");
        String celularContato = request.getParameter("celularContato");
        String linkExterno = request.getParameter("linkExterno");

        // Atualizar projeto
        projeto.setNome(nome);
        projeto.setSobre(sobre);
        projeto.setPublicoAlvo(publicoAlvo);
        projeto.setUnidade(unidade);
        projeto.setEmailContato(emailContato);
        projeto.setCelularContato(celularContato);
        projeto.setLinkExterno(linkExterno);

        // Atualizar no DAO
        projetoDAO.atualizar(projeto);

        response.sendRedirect(request.getContextPath() + "/admin/projetoListar");
    }
}
