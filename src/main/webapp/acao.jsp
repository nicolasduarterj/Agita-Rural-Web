<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="modelos.Acao"%>
<%@ include file="/componentes/barraNav.jsp" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Agita Rural - Ação</title>
    <link rel="stylesheet" href="css/Style.css">
</head>

<body>

<% Acao[] acoes = (Acao[]) session.getAttribute("acoes");
   int id = Integer.parseInt(request.getParameter("id"));
   Acao acaoprincipal = acoes[id];
%>

<section style="background:#004d26; color:white; padding:50px 20px; text-align:center;">
    <h1>Ação "<%= acaoprincipal.getNome() %>"</h1>
</section>

<div class="container">

    <h2>Dados da ação</h2>

    <div class="card-container">

        <!-- CARD 1 -->
        <div class="card">
            <h3>Título</h3>
            <h4><%= acaoprincipal.getNome() %></h4>
        </div>
        
        <div class="card">
            <h3>Descrição</h3>
            <h4><%= acaoprincipal.getSobre() %></h4>
        </div>
        
        <div class="card">
            <h3>Taxa</h3>
            <h4><%= acaoprincipal.getTaxa() ? "Possui taxa" : "Grátis" %></h4>
        </div>

		<div class="card">
            <h3>Público-alvo</h3>
            <h4><%= acaoprincipal.getPublicoAlvo() %></h4>
        </div>
        
        <div class="card">
            <h3>Local</h3>
            <h4><%= acaoprincipal.getLocalizacao().getNome() %></h4>
            <p>Endereço: <%= acaoprincipal.getLocalizacao().getEndereco()%>; Ponto de referência: <%= acaoprincipal.getLocalizacao().getPontoDeReferencia() %></p>
        </div>
    </div>
</div>
</body>
</html>
