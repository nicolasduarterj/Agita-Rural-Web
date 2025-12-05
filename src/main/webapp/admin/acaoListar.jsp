<%@page import="java.time.LocalDate"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="modelos.Acao, modelos.Unidade, java.util.*, java.time.format.DateTimeFormatter" %>
<%@ include file="/componentes/barraNav.jsp" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Ações cadastradas</title>

    <!-- CSS externo -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/lista.css">
</head>
<body>

<%
    // Obtendo a lista de ações do request
    List<Acao> acoes = (List<Acao>) request.getAttribute("acoes");
    // Formatter para exibir LocalDateTime de forma amigável
%>

<section class="header-section">
    <h1>Ações Cadastradas</h1>
</section>

<div class="container">
    <table class="tabela-acoes">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Público</th>
            <th>Status</th>
            <th>Data de encerramento</th> <!-- Nova coluna -->
            <th>Unidade</th>
            <th>Ações</th>
        </tr>

        <% if (acoes != null && !acoes.isEmpty()) { 
               for (Acao acao : acoes) {
                   boolean encerrada = LocalDate.now().isAfter(acao.getDataFim());
        %>
        <tr>
            <td><%= acao.getId() %></td>
            <td><%= acao.getNome() %></td>
            <td><%= acao.getPublicoAlvo() %></td>
            <td>
                <% if (!encerrada) { %>
                    <span class="status-ativo">ATIVO</span>
                <% } else { %>
                    <span class="status-encerrado">ENCERRADO</span>
                <% } %>
            </td>
            <td>
                <%= encerrada ? acao.getDataFimFormatada() : "-" %>
            </td>
            <td>
                <%= acao.getUnidade() != null ? acao.getUnidade().getNome() : "N/A" %>
            </td>
            <td class="acoes-btns">
                <% if (!encerrada) { %>
                    <a href="<%= request.getContextPath() %>/admin/acaoMod?id=<%= acao.getId() %>" 
                       class="btn-editar">Editar</a>
                    <a href="<%= request.getContextPath() %>/admin/encerrarAcao?id=<%= acao.getId() %>" 
                       class="btn-encerrar">Encerrar</a>
                <% } else { %>
                    <span style="color:gray;">Não editável</span>
                <% } %>
            </td>
        </tr>
        <%   } // fim do for
           } else { %>
        <tr>
            <td colspan="7" class="nenhuma-acao">
                Nenhuma ação cadastrada.
            </td>
        </tr>
        <% } %>
    </table>
</div>

</body>
</html>
