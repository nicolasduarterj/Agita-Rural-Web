package servlets.admin;

import java.io.IOException;

import dao.AdminDAO;
import modelos.Representante;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginadmin")
public class LoginAdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        AdminDAO dao = new AdminDAO();
        Representante admin = dao.autenticar(email, senha);

        if (admin != null) {
            HttpSession sessao = request.getSession(true);
            sessao.setAttribute("adminLogado", admin);
            response.sendRedirect(request.getContextPath() + "/admin/painelDeControle.jsp");
        } else {
        	response.sendRedirect(request.getContextPath() + "/loginadmin.jsp?erro=1");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	response.sendRedirect(request.getContextPath() + "/loginadmin.jsp");
    }
}
