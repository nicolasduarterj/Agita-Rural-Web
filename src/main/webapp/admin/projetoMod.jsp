<%@ page contentType="text/html;charset=UTF-8" language="java" import="modelos.Projeto, modelos.Unidade, java.util.*" %>
<%@ include file="/componentes/barraNav.jsp" %>

<%
    Projeto projeto = (Projeto) request.getAttribute("projeto");
    List<Unidade> unidades = (List<Unidade>) request.getAttribute("unidades");

    if (projeto == null) {
        response.sendRedirect(request.getContextPath() + "/admin/projetoListar.jsp");
        return;
    }

    boolean encerrada = projeto.getDataFim() != null;
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Projeto</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/form.css">
</head>
<body>

<section style="background:#004d26; color:white; padding:30px; text-align:center;">
    <h1>Editar Projeto: <%= projeto.getNome() %></h1>
    <% if (encerrada) { %>
        <p style="color:yellow;">Este projeto já foi encerrado e não pode ser editado.</p>
    <% } %>
</section>

<div class="container">
    <form action="<%= request.getContextPath() %>/admin/projetoSalvar" method="post">
        <input type="hidden" name="id" value="<%= projeto.getId() %>">

        <div class="form-group">
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name="nome" value="<%= projeto.getNome() %>" required <%= encerrada ? "disabled" : "" %>>
        </div>

        <div class="form-group">
            <label for="sobre">Descrição:</label>
            <textarea id="sobre" name="sobre" rows="4" <%= encerrada ? "disabled" : "" %>><%= projeto.getSobre() %></textarea>
        </div>

        <div class="form-group">
            <label for="publicoAlvo">Público-Alvo:</label>
            <input type="text" id="publicoAlvo" name="publicoAlvo" value="<%= projeto.getPublicoAlvo() %>" <%= encerrada ? "disabled" : "" %>>
        </div>

        <div class="form-group">
            <label for="unidadeId">Unidade:</label>
            <select id="unidadeId" name="unidadeId" <%= encerrada ? "disabled" : "" %>>
                <% for (Unidade u : unidades) { %>
                    <option value="<%= u.getId() %>" 
                        <%= projeto.getUnidade() != null && projeto.getUnidade().getId() == u.getId() ? "selected" : "" %>>
                        <%= u.getNome() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div class="form-group">
            <label for="emailContato">Email de Contato:</label>
            <input type="email" id="emailContato" name="emailContato" value="<%= projeto.getEmailContato() %>" <%= encerrada ? "disabled" : "" %>>
        </div>

        <div class="form-group">
            <label for="celularContato">Celular de Contato:</label>
            <input type="text" id="celularContato" name="celularContato" value="<%= projeto.getCelularContato() %>" <%= encerrada ? "disabled" : "" %>>
        </div>

        <div class="form-group">
            <label for="linkExterno">Link Externo:</label>
            <input type="text" id="linkExterno" name="linkExterno" value="<%= projeto.getLinkExterno() %>" <%= encerrada ? "disabled" : "" %>>
        </div>

        <div class="form-actions">
            <% if (!encerrada) { %>
                <button type="submit" class="btn-salvar">Salvar Alterações</button>
            <% } %>
        </div>
    </form>
</div>

</body>
</html>
