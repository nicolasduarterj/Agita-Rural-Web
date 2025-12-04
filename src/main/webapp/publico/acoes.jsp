<%@page import="modelos.Acao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/componentes/barraNav.jsp" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Agita Rural - Ações</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/acoes.css">
</head>

<%
    List<Acao> acoes = (List<Acao>) request.getAttribute("acoes");    
%>

<body>
	<div class="container">
		<h2>Ações</h2>
		<div>
			<form class="form-filtro" action="<%= request.getContextPath() %>/acoes" method="get">
				<div>
					<label for="titulo">Nome da ação</label>
					<input type="text" id="titulo" name="nomeAcao" value="${param.nomeAcao}" autocomplete="off">
				</div>
				<div>
					<label>
						Sem taxas
					</label>
					<input type="checkbox" name="semTaxa" value="true" ${param.semTaxa == 'true' ? 'checked' : ''}>
				</div>
				<div class="status-checkbox">
					<div>
						<span>Status</span>
					</div>
					<div class="escolhas-checkbox">
						<div class="escolha">
							<label>						
								Ativo
							</label>
							<input type="checkbox" name="status" value="ATIVO" ${param.status == 'ATIVO' ? 'checked' : ''}>
						</div>
						
						<div class="escolha">
							<label>						
								Pendente
							</label>
							<input type="checkbox" name="status" value="PAUSADO" ${param.status == 'PAUSADO' ? 'checked' : ''}>
						</div>
						
						<div class="escolha">
							<label>
								Descontinuado
							</label>
							<input type="checkbox" name="status" value="DESCONTINUADO" ${param.status == 'DESCONTINUADO' ? 'checked' : ''}>
						</div>
					</div>
				</div>
				<div>
					<button type="submit">Filtrar</button>
				</div>
			</form>
		</div>
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