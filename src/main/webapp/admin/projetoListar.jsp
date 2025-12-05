<%@ page contentType="text/html;charset=UTF-8" language="java" import="modelos.Projeto, java.util.*, java.time.format.DateTimeFormatter" %>
<%@ include file="/componentes/barraNav.jsp" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Projetos cadastrados</title>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/lista.css">
</head>

<body>

<%
    List<Projeto> projetos = (List<Projeto>) request.getAttribute("projetos");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
%>

<section class="header-section">
    <h1>Projetos Cadastrados</h1>
</section>

<div class="container">
    <table class="tabela-acoes">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Público-Alvo</th>
            <th>Status</th>
            <th>Data Início</th>
            <th>Data Fim</th>
            <th>Unidade</th>
            <th>Ações</th>
        </tr>

        <% if (projetos != null && !projetos.isEmpty()) { 
               for (Projeto projeto : projetos) {
                   boolean encerrado = projeto.getDataFim() != null;
        %>
        <tr>
            <td><%= projeto.getId() %></td>
            <td><%= projeto.getNome() %></td>
            <td><%= projeto.getPublicoAlvo() %></td>
            <td>
                <% if (!encerrado) { %>
                    <span class="status-ativo">ATIVO</span>
                <% } else { %>
                    <span class="status-encerrado">ENCERRADO</span>
                <% } %>
            </td>
            <td><%= projeto.getDataInicio() != null ? projeto.getDataInicio().format(formatter) : "-" %></td>
            <td><%= encerrado ? projeto.getDataFim().format(formatter) : "-" %></td>
            <td><%= projeto.getUnidade() != null ? projeto.getUnidade().getNome() : "N/A" %></td>
            <td class="acoes-btns">
                <% if (!encerrado) { %>
                    <a href="<%= request.getContextPath() %>/admin/projetoMod?id=<%= projeto.getId() %>" 
   						class="btn-editar">Editar</a>
                    <a href="<%= request.getContextPath() %>/admin/projeto/encerrar?id=<%= projeto.getId() %>" 
                       class="btn-encerrar">Encerrar</a>
                <% } else { %>
                    <span style="color:gray;">Não editável</span>
                <% } %>
            </td>
        </tr>
        <%   } // fim do for
           } else { %>
        <tr>
            <td colspan="8" class="nenhum-projeto">
                Nenhum projeto cadastrado.
            </td>
        </tr>
        <% } %>
    </table>
</div>

</body>
</html>
