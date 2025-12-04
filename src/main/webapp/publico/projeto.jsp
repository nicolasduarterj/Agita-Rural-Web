<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="modelos.Acao, modelos.Projeto"%>
<%@ include file="/componentes/barraNav.jsp" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Agita Rural - Ação</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<%
	Projeto projeto = (Projeto) request.getAttribute("projeto");
	String acoesCards = "";

	for (Acao acao : projeto.getAcoes()) {
	   acoesCards += String.format("<div class=\"card\">" +
	            "<h3>%s</h3>" +
	            "<p>%s</p>" +
	            "<a href=\"acao?id=%d\" class=\"button\">Ver detalhes</a>" +
	        "</div>\n\n", acao.getNome(), acao.getSobre(), acao.getId());
	   
	}
	
	if (acoesCards.equals(""))
		acoesCards += "<div class=\"card\"" + "<h3>Sem ações registradas</h3>" + "</div>";
%>

<section style="background:#004d26; color:white; padding:50px 20px; text-align:center;">
    <h1>Projeto "<%= projeto.getNome() %>"</h1>
</section>

<div class="container">

    <h2>Dados do projeto</h2>

    <div class="card-container">

        <!-- CARD 1 -->
        <div class="card">
            <h3>Título</h3>
            <h4><%= projeto.getNome() %></h4>
        </div>
        
        <div class="card">
            <h3>Descrição</h3>
            <h4><%= projeto.getSobre() %></h4>
        </div>
        
		<div class="card">
            <h3>Público-alvo</h3>
            <h4><%= projeto.getPublicoAlvo() %></h4>
        </div>
        
        <div class="card">
            <h3>Local para informações</h3>
            <h4><%= projeto.getLocalParaInformacao().getNome() %></h4>
            <p>Endereço: <%= projeto.getLocalParaInformacao().getEndereco()%>; Ponto de referência: <%= projeto.getLocalParaInformacao().getPontoDeReferencia() %></p>
        </div>
    </div>
    
    <h2>Ações do projeto</h2>
    
    <div class="card-container">
    	<%= acoesCards %>
    </div>
</div>
</body>
</html>
