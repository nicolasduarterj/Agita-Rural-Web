<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="modelos.Acao, modelos.Projeto, java.util.*" %>

<%@ include file="/componentes/barraNav.jsp" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Agita Rural - Home</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>

<body>

<%
    Projeto principal = (Projeto) request.getAttribute("projetoPrincipal");
    List<Acao> acoes = (List<Acao>) request.getAttribute("acoes");
%>

<section style="background:#004d26; color:white; padding:50px 20px; text-align:center;">
    <h1>Bem-vindo ao Agita Rural</h1>
    <p>Conheça ações e projetos da UFRRJ.</p>
</section>

<!-- PROJETO DESTAQUE -->
<div class="container">
    <h2>Projeto Destaque</h2>

    <div class="card-container">

        <div class="card">
            <h3><%= principal.getNome() %></h3>
            <p><%= principal.getSobre() %></p>
            <a href="<%= request.getContextPath() %>/projeto?id=<%= principal.getId() %>" class="button">Ver detalhes</a>
        </div>

    </div>
</div>

<!-- AÇÕES DESTAQUE -->
<div class="container">
    <h2>Ações Destaques</h2>
    <div class="card-container">
    <% if (acoes != null && !acoes.isEmpty()) {%>
		<% for (Acao acao : acoes) {%>
			<div class="card">
	            <h3><%= acao.getNome() %></h3>
	            <p><%= acao.getSobre() %></p>
	            <p>Inicio: <%= acao.getDataInicioFormatada() %><p>
	           	<a href="<%= request.getContextPath()%>/acao?id=<%= acao.getId() %>" class="button">Ver detalhes</a>
		        </div>
				<% } %>
		<% } %>
    </div>
</div>
</body>
</html>
