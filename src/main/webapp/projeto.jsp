<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="modelos.Acao, modelos.Projeto"%>
<%@ include file="/componentes/barraNav.jsp" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Agita Rural - Ação</title>
    <link rel="stylesheet" href="css/Style.css">
</head>

<body>

<%  Projeto principal = (Projeto) session.getAttribute("projetoPrincipal"); 

	String acoesCards = "";
	int i = 0;

	for (Acao acao : principal.getAcoes()) {
	   acoesCards += String.format("<div class=\"card\">" +
	            "<h3>%s</h3>" +
	            "<p>%s</p>" +
	            "<a href=\"acao.jsp?id=%d\" class=\"button\">Ver detalhes</a>" +
	        "</div>\n\n", acao.getNome(), acao.getSobre(), i);
	   i++;
	}
%>

<section style="background:#004d26; color:white; padding:50px 20px; text-align:center;">
    <h1>Projeto "<%= principal.getNome() %>"</h1>
</section>

<div class="container">

    <h2>Dados do projeto</h2>

    <div class="card-container">

        <!-- CARD 1 -->
        <div class="card">
            <h3>Título</h3>
            <h4><%= principal.getNome() %></h4>
        </div>
        
        <div class="card">
            <h3>Descrição</h3>
            <h4><%= principal.getSobre() %></h4>
        </div>
        
		<div class="card">
            <h3>Público-alvo</h3>
            <h4><%= principal.getPublicoAlvo() %></h4>
        </div>
        
        <div class="card">
            <h3>Local para informações</h3>
            <h4><%= principal.getLocalParaInformacao().getNome() %></h4>
            <p>Endereço: <%= principal.getLocalParaInformacao().getEndereco()%>; Ponto de referência: <%= principal.getLocalParaInformacao().getPontoDeReferencia() %></p>
        </div>
    </div>
    
    <h2>Ações do projeto</h2>
    
    <div class="card-container">
    	<%= acoesCards %>
    </div>
</div>
</body>
</html>
