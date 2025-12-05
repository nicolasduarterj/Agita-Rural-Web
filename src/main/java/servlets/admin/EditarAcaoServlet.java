package servlets.admin;

import dao.AcaoDAO;
import dao.UnidadeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import modelos.Acao;
import modelos.Local;
import modelos.Unidade;

import java.io.IOException;

@WebServlet("/admin/atualizarAcao")
public class EditarAcaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        AcaoDAO acaoDAO = new AcaoDAO();
        Acao acao = acaoDAO.buscarPorId(id);

        if (acao == null) {
            request.setAttribute("erro", "Ação não encontrada para edição.");
            request.getRequestDispatcher("/admin/acaoListar.jsp").forward(request, response);
            return;
        }

        // Campos principais
        String sobre = request.getParameter("sobre");
        String publicoAlvo = request.getParameter("publicoAlvo");

        // Local
        String nomeLocal = request.getParameter("nomeLocal");
        String endereco = request.getParameter("endereco");
        String pontoRef = request.getParameter("pontoRef");
        Local novoLocal = new Local(nomeLocal, endereco, pontoRef);

        // Taxa e preço
        boolean possuiTaxa = request.getParameter("possuiTaxa") != null;
        String preco = request.getParameter("preco");

        // Unidade
        int unidadeId = Integer.parseInt(request.getParameter("unidadeId"));
        Unidade unidade = new UnidadeDAO().buscarPorId(unidadeId);

        // Contato e link
        String email = request.getParameter("email");
        String celular = request.getParameter("celular");
        String linkExterno = request.getParameter("linkExterno");

        // Atualizar ação
        acao.setSobre(sobre);
        acao.setPublicoAlvo(publicoAlvo);
        acao.setLocalizacao(novoLocal);
        acao.setUnidade(unidade);
        acao.setTaxa(possuiTaxa);
        acao.setPreco(possuiTaxa ? preco : null);
        acao.setEmail(email);
        acao.setCelular(celular);
        acao.setLinkExterno(linkExterno);

        response.sendRedirect(request.getContextPath() + "/admin/acaoListar");
    }
}

