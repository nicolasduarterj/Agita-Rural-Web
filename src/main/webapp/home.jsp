<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="modelos.Acao, modelos.Local"%>
<%@ include file="/componentes/barraNav.jsp" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Agita Rural - Home</title>
    <link rel="stylesheet" href="css/Style.css">
</head>

<body>

<section style="background:#004d26; color:white; padding:50px 20px; text-align:center;">
    <h1>Bem-vindo ao Agita Rural</h1>
    <p>Conheça ações e projetos da UFRRJ.</p>
</section>

<div class="container">

    <h2>Projetos Destaque</h2>

    <div class="card-container">

        <!-- CARD 1 -->
        <div class="card">
            <h3>Projeto Um</h3>
            <p>Projeto de número um.</p>
            <a href="projeto.jsp?id=1" class="button">Ver detalhes</a>
        </div>

        <!-- CARD 2 -->
        <div class="card">
            <h3>Projeto Dois</h3>
            <p>Projeto de número dois..</p>
            <a href="projeto.jsp?id=2" class="button">Ver detalhes</a>
        </div>

        <!-- CARD  3 -->
        <div class="card">
            <h3>Projeto Três</h3>
            <p>Projeto de número três.</p>
            <a href="projeto.jsp?id=3" class="button">Ver detalhes</a>
        </div>
        
        <!-- CARD  4 -->
        <div class="card">
            <h3>Projeto Quatro</h3>
            <p>Projeto de número quatro.</p>
            <a href="projeto.jsp?id=3" class="button">Ver detalhes</a>
        </div>
        
        <!-- CARD  5 -->
        <div class="card">
            <h3>Projeto Cinco</h3>
            <p>Projeto de número cinco.</p>
            <a href="projeto.jsp?id=3" class="button">Ver detalhes</a>
        </div>

    </div>
</div>


<% Acao acao1 = new Acao("Tênis", "Venha jogar tênis!", "Alunos", new Local("Quadra", "Rural", ""), null, null, false);
   Acao acao2 = new Acao("Jiu-jitsu", "Aprenda a se defender!", "Alunos", new Local("DEFIS", "Rural", ""), null, null, false);
   Acao acao3 = new Acao("Futebol", "Um clássico brasileiro!", "Alunos", new Local("Ginásio", "Rural", ""), null, null, false);
   
   session.setAttribute("acoes", new Acao[]{acao1, acao2, acao3});
%>

<div class="container">
	
    <h2>Ações Destaques</h2>
	
    <div class="card-container">

        <!-- CARD 1 -->
        <div class="card">
            <h3><%= acao1.getNome() %></h3>
            <p><%= acao1.getSobre() %></p>
            <a href="acao.jsp?id=0" class="button">Ver detalhes</a>
        </div>

        <!-- CARD 2 -->
        <div class="card">
            <h3><%= acao2.getNome() %></h3>
            <p><%= acao2.getSobre() %></p>
            <a href="acao.jsp?id=1" class="button">Ver detalhes</a>
        </div>

        <!-- CARD 3 -->
        <div class="card">
            <h3><%= acao3.getNome() %></h3>
            <p><%= acao3.getSobre() %></p>
            <a href="acao.jsp?id=2" class="button">Ver detalhes</a>
        </div>
    </div>
</div>
</body>
</html>
